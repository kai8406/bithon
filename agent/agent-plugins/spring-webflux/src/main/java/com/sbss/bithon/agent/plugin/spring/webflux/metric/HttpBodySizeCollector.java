/*
 *    Copyright 2020 bithon.cn
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

package com.sbss.bithon.agent.plugin.spring.webflux.metric;

import com.sbss.bithon.agent.core.tracing.propagation.ITracePropagator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import reactor.netty.channel.ChannelOperations;
import reactor.netty.http.HttpInfos;
import reactor.netty.http.server.HttpServerRequest;
import shaded.org.slf4j.Logger;
import shaded.org.slf4j.LoggerFactory;

/**
 * @author Frank ChenØ
 * @date 7/10/21 4:20 pm
 */
public class HttpBodySizeCollector extends ChannelDuplexHandler {

    private static final Logger LOG = LoggerFactory.getLogger(HttpBodySizeCollector.class);

    final HttpIncomingRequestMetricCollector collector;
    long dataReceived;
    long dataSent;

    static Class<?> httpServerOperationClass = null;

    static {
        try {
            httpServerOperationClass = Class.forName("reactor.netty.http.server.HttpServerOperations",
                                                     false,
                                                     ChannelHandlerContext.class.getClassLoader());
        } catch (ClassNotFoundException ignored) {
            LOG.error("Unable to find HttpServerOperations. HTTP metrics may not work as expected.");
        }
    }

    public HttpBodySizeCollector(HttpIncomingRequestMetricCollector collector) {
        this.collector = collector;
    }

    private String getHttOperationPath(Object obj) {
        return ((HttpInfos) obj).fullPath();
    }

    private HttpHeaders getRequestHeaders(Object obj) {
        return ((HttpServerRequest) obj).requestHeaders();
    }

    @Override
    @SuppressWarnings("FutureReturnValueIgnored")
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        if (msg instanceof HttpResponse) {
            if (((HttpResponse) msg).status().equals(HttpResponseStatus.CONTINUE)) {
                //"FutureReturnValueIgnored" this is deliberate
                ctx.write(msg, promise);
                return;
            }
        }

        if (msg instanceof ByteBufHolder) {
            dataSent += ((ByteBufHolder) msg).content().readableBytes();
        } else if (msg instanceof ByteBuf) {
            dataSent += ((ByteBuf) msg).readableBytes();
        }

        if (msg instanceof LastHttpContent) {
            promise.addListener(future -> {
                ChannelOperations<?, ?> channelOps = ChannelOperations.get(ctx.channel());
                if (httpServerOperationClass != null && channelOps.getClass().isAssignableFrom(httpServerOperationClass)) {
                    recordWrite(channelOps, dataSent);
                }

                dataSent = 0;
            });
        }

        //"FutureReturnValueIgnored" this is deliberate
        ctx.write(msg, promise);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof ByteBufHolder) {
            dataReceived += ((ByteBufHolder) msg).content().readableBytes();
        } else if (msg instanceof ByteBuf) {
            dataReceived += ((ByteBuf) msg).readableBytes();
        }

        if (msg instanceof LastHttpContent) {
            ChannelOperations<?, ?> channelOps = ChannelOperations.get(ctx.channel());
            if (httpServerOperationClass != null && channelOps.getClass().isAssignableFrom(httpServerOperationClass)) {
                recordRead(channelOps, dataReceived);
            }

            dataReceived = 0;
        }

        ctx.fireChannelRead(msg);
    }

    private void recordRead(ChannelOperations<?, ?> channelOps, long dataReceived) {
        try {
            collector.getOrCreateMetric(this.getRequestHeaders(channelOps).get(ITracePropagator.BITHON_SRC_APPLICATION),
                                        this.getHttOperationPath(channelOps)).updateBytes(dataReceived, 0);
        } catch (Exception e) {
            LOG.error("", e);
        }
    }

    private void recordWrite(ChannelOperations<?, ?> channelOps, long dataSent) {
        try {
            collector.getOrCreateMetric(this.getRequestHeaders(channelOps).get(ITracePropagator.BITHON_SRC_APPLICATION),
                                        this.getHttOperationPath(channelOps)).updateBytes(0, dataSent);
        } catch (Exception e) {
            LOG.error("", e);
        }
    }
}
