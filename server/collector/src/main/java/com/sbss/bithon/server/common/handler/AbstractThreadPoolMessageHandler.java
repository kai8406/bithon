package com.sbss.bithon.server.common.handler;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/10 4:55 下午
 */
@Slf4j
public abstract class AbstractThreadPoolMessageHandler<MSG> implements IMessageHandler<MSG> {
    protected final ThreadPoolExecutor executor;

    public AbstractThreadPoolMessageHandler(int corePoolSize,
                                            int maxPoolSize,
                                            Duration keepAliveTime,
                                            int queueSize) {
        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                                          keepAliveTime.getSeconds(),
                                          TimeUnit.SECONDS,
                                          new LinkedBlockingQueue<>(queueSize),
                                          new ThreadPoolExecutor.DiscardPolicy());

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutting down executor [{}]", this.getType());
            executor.shutdown();
        }));
    }

    @Override
    public void submit(MSG msg) {
        executor.submit(() -> {
            try {
                onMessage(msg);
            } catch (Exception e) {
                log.error("Error process message", e);
            }
        });
    }

    protected abstract void onMessage(MSG msg) throws Exception;
}