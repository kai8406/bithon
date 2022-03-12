/*
 *    Copyright 2020 bithon.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.bithon.component.brpc.channel;

import org.bithon.component.brpc.ServiceRegistry;
import org.bithon.component.brpc.endpoint.EndPoint;
import org.bithon.component.brpc.endpoint.IEndPointProvider;
import org.bithon.component.brpc.endpoint.SingleEndPointProvider;
import org.bithon.component.brpc.exception.CallerSideException;
import org.bithon.component.brpc.exception.ServiceInvocationException;
import org.bithon.component.brpc.invocation.ServiceStubFactory;
import org.bithon.component.brpc.message.in.ServiceMessageInDecoder;
import org.bithon.component.brpc.message.out.ServiceMessageOutEncoder;
import org.bithon.component.commons.concurrency.NamedThreadFactory;
import org.bithon.component.commons.logging.ILogAdaptor;
import org.bithon.component.commons.logging.LoggerFactory;
import shaded.io.netty.bootstrap.Bootstrap;
import shaded.io.netty.channel.Channel;
import shaded.io.netty.channel.ChannelHandlerContext;
import shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import shaded.io.netty.channel.ChannelInitializer;
import shaded.io.netty.channel.ChannelOption;
import shaded.io.netty.channel.ChannelPipeline;
import shaded.io.netty.channel.nio.NioEventLoopGroup;
import shaded.io.netty.channel.socket.SocketChannel;
import shaded.io.netty.channel.socket.nio.NioSocketChannel;
import shaded.io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import shaded.io.netty.handler.codec.LengthFieldPrepender;
import shaded.io.netty.util.concurrent.Future;

import java.io.Closeable;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Should only be used at the client side
 */
public class ClientChannel implements IChannelWriter, Closeable {
    //
    // channel
    //
    public static final int MAX_RETRY = 30;
    private static final ILogAdaptor log = LoggerFactory.getLogger(ClientChannel.class);
    private final Bootstrap bootstrap;
    private final AtomicReference<Channel> channel = new AtomicReference<>();
    private final IEndPointProvider endPointProvider;
    private final ServiceRegistry serviceRegistry = new ServiceRegistry();
    private NioEventLoopGroup bossGroup;
    private Duration retryInterval;
    private int maxRetry;
    private long connectionTimestamp;

    /**
     * a logic name of the client, which could be used for the servers to find client instances
     */
    private String appName;

    public ClientChannel(String host, int port) {
        this(new SingleEndPointProvider(host, port));
    }

    /**
     * create a ClientChannel object with 1 worker threads
     */
    public ClientChannel(IEndPointProvider endPointProvider) {
        this(endPointProvider, 1);
    }

    /**
     * @param nThreads if it's 0, worker threads will be default to Runtime.getRuntime().availableProcessors() * 2
     */
    public ClientChannel(IEndPointProvider endPointProvider, int nThreads) {
        this.endPointProvider = endPointProvider;
        this.maxRetry = MAX_RETRY;
        this.retryInterval = Duration.ofMillis(100);

        bossGroup = new NioEventLoopGroup(nThreads, NamedThreadFactory.of("brpc-client"));
        bootstrap = new Bootstrap();
        bootstrap.group(bossGroup)
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     public void initChannel(SocketChannel ch) {
                         ChannelPipeline pipeline = ch.pipeline();
                         pipeline.addLast("frameDecoder",
                                          new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                         pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                         pipeline.addLast("decoder", new ServiceMessageInDecoder());
                         pipeline.addLast("encoder", new ServiceMessageOutEncoder());
                         pipeline.addLast(new ClientChannelManager());
                         pipeline.addLast(new ServiceMessageChannelHandler(serviceRegistry));
                     }
                 });
    }

    @Override
    public Channel getChannel() {
        return channel.get();
    }

    @Override
    public void writeAndFlush(Object obj) {
        Channel ch = channel.get();
        if (ch == null) {
            throw new ServiceInvocationException("Client channel is closed");
        }
        ch.writeAndFlush(obj);
    }

    @Override
    public synchronized void connect() {
        if (bossGroup == null) {
            throw new IllegalStateException("client channel has been shutdown");
        }
        if (channel.get() == null) {
            doConnect(maxRetry);
        }
    }

    @Override
    public synchronized void disconnect() {
        Channel ch = channel.getAndSet(null);
        if (ch != null) {
            ch.disconnect();
        }
        connectionTimestamp = 0;
    }

    @Override
    public long getConnectionLifeTime() {
        return connectionTimestamp > 0 ? System.currentTimeMillis() - connectionTimestamp : 0;
    }

    /**
     * must be called before {@link #getRemoteService(Class)}
     */
    public ClientChannel applicationName(String appName) {
        this.appName = appName;
        return this;
    }

    public ClientChannel configureRetry(int maxRetry, Duration interval) {
        this.maxRetry = maxRetry;
        this.retryInterval = interval;
        return this;
    }

    @Override
    public void close() {
        if (this.bossGroup != null) {
            try {
                this.bossGroup.shutdownGracefully().sync();
            } catch (InterruptedException ignored) {
            }
        }
        this.bossGroup = null;
        this.channel.getAndSet(null);
    }

    private void doConnect(int maxRetry) {
        EndPoint endpoint = null;
        for (int i = 0; i < maxRetry; i++) {
            endpoint = endPointProvider.getEndpoint();
            try {
                Future<?> connectFuture = bootstrap.connect(endpoint.getHost(), endpoint.getPort());
                connectFuture.await(200, TimeUnit.MILLISECONDS);
                if (connectFuture.isSuccess()) {
                    connectionTimestamp = System.currentTimeMillis();
                    log.info("Successfully connected to server({}:{})", endpoint.getHost(), endpoint.getPort());
                    return;
                }
                int leftCount = maxRetry - i - 1;
                if (leftCount > 0) {
                    log.warn("Unable to connect to server({}:{})。Left retry count:{}",
                             endpoint.getHost(),
                             endpoint.getPort(),
                             maxRetry - i - 1);
                    Thread.sleep(retryInterval.toMillis());
                }
            } catch (InterruptedException e) {
                throw new CallerSideException("Unable to connect to server, interrupted");
            }
        }
        throw new CallerSideException("Unable to connect to server(%s:%s)", endpoint.getHost(), endpoint.getPort());
    }

    public ClientChannel bindService(Object serviceImpl) {
        serviceRegistry.addService(serviceImpl);
        return this;
    }

    public <T> T getRemoteService(Class<T> serviceType) {
        return ServiceStubFactory.create(this.appName, this, serviceType);
    }

    class ClientChannelManager extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) {
            ClientChannel.this.channel.getAndSet(ctx.channel());
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            ClientChannel.this.channel.getAndSet(null);
            super.channelInactive(ctx);
        }
    }
}
