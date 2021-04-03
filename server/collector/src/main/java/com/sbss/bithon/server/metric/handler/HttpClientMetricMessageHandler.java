package com.sbss.bithon.server.metric.handler;

import com.sbss.bithon.component.db.dao.EndPointType;
import com.sbss.bithon.server.common.service.UriNormalizer;
import com.sbss.bithon.server.common.utils.NetworkUtils;
import com.sbss.bithon.server.meta.EndPointLink;
import com.sbss.bithon.server.meta.storage.IMetaStorage;
import com.sbss.bithon.server.metric.DataSourceSchemaManager;
import com.sbss.bithon.server.metric.storage.IMetricStorage;
import lombok.extern.slf4j.Slf4j;
import org.jooq.tools.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/10 4:55 下午
 */
@Slf4j
@Service
public class HttpClientMetricMessageHandler extends AbstractMetricMessageHandler {

    private final UriNormalizer uriNormalizer;

    public HttpClientMetricMessageHandler(UriNormalizer uriNormalizer,
                                          IMetaStorage metaStorage,
                                          IMetricStorage metricStorage,
                                          DataSourceSchemaManager dataSourceSchemaManager) throws IOException {
        super("http-client-metrics",
              metaStorage,
              metricStorage,
              dataSourceSchemaManager,
              2,
              20,
              Duration.ofSeconds(60), 4096);
        this.uriNormalizer = uriNormalizer;
    }

    @Override
    protected boolean beforeProcess(GenericMetricMessage metricObject) throws Exception {
        if (metricObject.getLong("requestCount") <= 0) {
            return false;
        }

        URI uri = new URI(metricObject.getString("uri"));
        UriNormalizer.NormalizedResult result = uriNormalizer.normalize(metricObject.getApplicationName(),
                                                                        NetworkUtils.formatUri(uri));
        if (result.getUri() == null) {
            return false;
        }

        String targetHostPort = toHostPort(uri.getHost(), uri.getPort());
        if (targetHostPort == null) {
            log.warn("TargetHost is blank. {}", metricObject);
            return false;
        }

        if (NetworkUtils.isIpAddress(uri.getHost())) {

            // try to get application info by instance name to see if its an internal application
            String targetApplicationName = getMetaStorage().getApplicationByInstance(targetHostPort);

            if (targetApplicationName != null) {
                metricObject.set("endpoint", EndPointLink.builder()
                                                         .timestamp(metricObject.getTimestamp())
                                                         .srcEndpointType(EndPointType.APPLICATION)
                                                         .srcEndpoint(metricObject.getApplicationName())
                                                         .dstEndpointType(EndPointType.APPLICATION)
                                                         .dstEndpoint(targetApplicationName)
                                                         .interval(metricObject.getLong("interval"))
                                                         .callCount(metricObject.getLong("requestCount"))
                                                         .responseTime(metricObject.getLong("responseTime"))
                                                         .minResponseTime(metricObject.getLong("minResponseTime"))
                                                         .maxResponseTime(metricObject.getLong("maxResponseTime"))
                                                         .build());
            } else {
                //
                // if the target application has not been in service yet,
                // it of course can't be found in the metadata storage
                //
                // TODO: This record should be fixed when a new instance is inserted into the metadata storage
                metricObject.set("endpoint", EndPointLink.builder()
                                                         .timestamp(metricObject.getTimestamp())
                                                         .srcEndpointType(EndPointType.APPLICATION)
                                                         .srcEndpoint(metricObject.getApplicationName())
                                                         .dstEndpointType(EndPointType.WEB_SERVICE)
                                                         .dstEndpoint(targetHostPort)
                                                         .interval(metricObject.getLong("interval"))
                                                         .callCount(metricObject.getLong("requestCount"))
                                                         .responseTime(metricObject.getLong("responseTime"))
                                                         .minResponseTime(metricObject.getLong("minResponseTime"))
                                                         .maxResponseTime(metricObject.getLong("maxResponseTime"))
                                                         .build());
            }
        } else { // if uri.getHost is not IP address
            //TODO: targetHostPort may be an service name if it's a service call such as point to point via service auto discovery
            if (getMetaStorage().isApplicationExist(targetHostPort)) {
                metricObject.set("endpoint", EndPointLink.builder()
                                                         .timestamp(metricObject.getTimestamp())
                                                         .srcEndpointType(EndPointType.APPLICATION)
                                                         .srcEndpoint(metricObject.getApplicationName())
                                                         .dstEndpointType(EndPointType.APPLICATION)
                                                         .dstEndpoint(targetHostPort)
                                                         .interval(metricObject.getLong("interval"))
                                                         .callCount(metricObject.getLong("requestCount"))
                                                         .responseTime(metricObject.getLong("responseTime"))
                                                         .minResponseTime(metricObject.getLong("minResponseTime"))
                                                         .maxResponseTime(metricObject.getLong("maxResponseTime"))
                                                         .build());
            } else {
                metricObject.set("endpoint", EndPointLink.builder()
                                                         .timestamp(metricObject.getTimestamp())
                                                         .srcEndpointType(EndPointType.APPLICATION)
                                                         .srcEndpoint(metricObject.getApplicationName())
                                                         .dstEndpointType(EndPointType.WEB_SERVICE)
                                                         .dstEndpoint(targetHostPort)
                                                         .interval(metricObject.getLong("interval"))
                                                         .callCount(metricObject.getLong("requestCount"))
                                                         .responseTime(metricObject.getLong("responseTime"))
                                                         .minResponseTime(metricObject.getLong("minResponseTime"))
                                                         .maxResponseTime(metricObject.getLong("maxResponseTime"))
                                                         .build());
            }
        }
        return true;
    }

    private String toHostPort(String targetHost, int targetPort) {
        if (StringUtils.isBlank(targetHost)) {
            return null;
        }

        if (targetPort < 0) {
            return targetHost;
        } else {
            return targetHost + ":" + targetPort;
        }
    }
}