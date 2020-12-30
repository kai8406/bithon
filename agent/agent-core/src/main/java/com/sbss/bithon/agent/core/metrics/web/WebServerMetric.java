package com.sbss.bithon.agent.core.metrics.web;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/13 10:51 下午
 */
public class WebServerMetric {

    private final WebServerType serverType;
    private final long connectionCount;
    private final long maxConnections;
    private final long activeThreads;
    private final long maxThreads;

    public WebServerType getServerType() {
        return serverType;
    }

    public long getConnectionCount() {
        return connectionCount;
    }

    public long getMaxConnections() {
        return maxConnections;
    }

    public long getActiveThreads() {
        return activeThreads;
    }

    public long getMaxThreads() {
        return maxThreads;
    }

    public WebServerMetric(WebServerType serverType, long connectionCount, long maxConnections, long activeThreads, long maxThreads) {
        this.serverType = serverType;
        this.connectionCount = connectionCount;
        this.maxConnections = maxConnections;
        this.activeThreads = activeThreads;
        this.maxThreads = maxThreads;
    }
}
