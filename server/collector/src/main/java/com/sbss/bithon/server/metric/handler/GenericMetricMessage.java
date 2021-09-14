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

package com.sbss.bithon.server.metric.handler;

import com.sbss.bithon.agent.rpc.brpc.BrpcMessageHeader;
import com.sbss.bithon.server.common.utils.ReflectionUtils;

import java.util.HashMap;

/**
 * TODO: cache reflection results
 *
 * @author frank.chen021@outlook.com
 * @date 2021/3/15
 */
public class GenericMetricMessage extends HashMap<String, Object> {

    public static GenericMetricMessage of(BrpcMessageHeader header, Object message) {
        GenericMetricMessage metricMessage = new GenericMetricMessage();
        ReflectionUtils.getFields(header, metricMessage);
        ReflectionUtils.getFields(message, metricMessage);
        return metricMessage;
    }

    public static GenericMetricMessage of(com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header, Object message) {
        GenericMetricMessage metricMessage = new GenericMetricMessage();
        ReflectionUtils.getFields(header, metricMessage);
        ReflectionUtils.getFields(message, metricMessage);
        return metricMessage;
    }

    public long getTimestamp() {
        return (long) this.get("timestamp");
    }

    public String getApplicationName() {
        return (String) this.get("appName");
    }

    public String getApplicationEnv() {
        return (String) this.get("env");
    }

    public String getInstanceName() {
        return (String) this.get("instanceName");
    }

    public long getLong(String prop) {
        return ((Number) this.getOrDefault(prop, 0L)).longValue();
    }

    public <T> T getAs(String prop) {
        return (T) this.get(prop);
    }

    public void set(String prop, Object value) {
        this.put(prop, value);
    }

    public String getString(String prop) {
        return (String) this.get(prop);
    }
}
