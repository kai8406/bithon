package com.sbss.bithon.server.metric.handler;

import com.sbss.bithon.server.meta.storage.IMetaStorage;
import com.sbss.bithon.server.metric.DataSourceSchemaManager;
import com.sbss.bithon.server.metric.storage.IMetricStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/10 4:31 下午
 */
@Slf4j
@Service
public class ThreadPoolMetricMessageHandler extends AbstractMetricMessageHandler {

    public ThreadPoolMetricMessageHandler(IMetaStorage metaStorage,
                                          DataSourceSchemaManager dataSourceSchemaManager,
                                          IMetricStorage metricStorage) throws IOException {
        super("thread-pool-metrics",
              metaStorage,
              metricStorage,
              dataSourceSchemaManager,
              5,
              20,
              Duration.ofSeconds(60),
              4096);
    }
}