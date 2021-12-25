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

package org.bithon.server.tracing.sink;

import lombok.Data;

import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/4 8:28 下午
 */
@Data
public class TraceSpan {
    public String appName;
    public String instanceName;
    public String traceId;
    public String spanId;
    public String kind;
    public String parentSpanId;
    public String parentApplication;
    public Map<String, String> tags;
    public long costTime;
    public long startTime;
    public long endTime;
    public String name;
    public String clazz;
    public String method;

    public boolean containsTag(String name) {
        return tags.containsKey(name);
    }

    public String getTag(String name) {
        return tags.get(name);
    }

    @Override
    public String toString() {
        return "TraceSpan{" +
               "appName='" + appName + '\'' +
               ", instanceName='" + instanceName + '\'' +
               ", traceId='" + traceId + '\'' +
               ", spanId='" + spanId + '\'' +
               ", kind='" + kind + '\'' +
               ", parentSpanId='" + parentSpanId + '\'' +
               ", parentApplication='" + parentApplication + '\'' +
               ", tags=" + tags +
               ", costTime=" + costTime +
               ", startTime=" + startTime +
               ", endTime=" + endTime +
               ", name='" + name + '\'' +
               ", clazz='" + clazz + '\'' +
               ", method='" + method + '\'' +
               '}';
    }
}
