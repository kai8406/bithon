package com.sbss.bithon.collector.datasource.storage;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sbss.bithon.collector.datasource.DataSourceSchema;
import com.sbss.bithon.collector.common.utils.datetime.TimeSpan;

import java.util.List;
import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2020/12/11 11:09 上午
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(value = {
    //@JsonSubTypes.Type(name = "druid", value = DruidStorageReader.class)
})
public interface IMetricReader {

    List<Map<String, Object>> getMetricValueList(TimeSpan start,
                                                 TimeSpan end,
                                                 DataSourceSchema dataSourceSchema,
                                                 List<DimensionCondition> dimensions,
                                                 List<String> metrics);

    List<Map<String, Object>> getMetricValueList(String sql);
}
