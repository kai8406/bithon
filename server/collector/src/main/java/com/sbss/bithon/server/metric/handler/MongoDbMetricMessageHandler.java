package com.sbss.bithon.server.metric.handler;

import com.sbss.bithon.component.db.dao.EndPointType;
import com.sbss.bithon.server.meta.EndPointLink;
import com.sbss.bithon.server.meta.storage.IMetaStorage;
import com.sbss.bithon.server.metric.DataSourceSchemaManager;
import com.sbss.bithon.server.metric.storage.IMetricStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/28 12:36
 */
@Slf4j
@Service
public class MongoDbMetricMessageHandler extends AbstractMetricMessageHandler {

    public MongoDbMetricMessageHandler(IMetaStorage metaStorage,
                                       IMetricStorage metricStorage,
                                       DataSourceSchemaManager dataSourceSchemaManager) throws IOException {
        super("mongodb-metrics",
              metaStorage,
              metricStorage,
              dataSourceSchemaManager,
              1,
              5,
              Duration.ofSeconds(60),
              2048);
    }

    @Override
    protected boolean beforeProcess(GenericMetricMessage metricObject) {
        metricObject.set("endpoint", EndPointLink.builder()
                                                 .timestamp(metricObject.getTimestamp())
                                                 .srcEndpointType(EndPointType.APPLICATION)
                                                 .srcEndpoint(metricObject.getApplicationName())
                                                 .dstEndpointType(EndPointType.DB_MONGO)
                                                 .dstEndpoint(metricObject.getString("server"))
                                                 // metric
                                                 .interval(metricObject.getLong("interval"))
                                                 .errorCount(metricObject.getLong("exceptionCount"))
                                                 .callCount(metricObject.getLong("callCount"))
                                                 .responseTime(metricObject.getLong("responseTime"))
                                                 .minResponseTime(metricObject.getLong("minResponseTime"))
                                                 .maxResponseTime(metricObject.getLong("maxResponseTime"))
                                                 .build());
        return true;
    }
}