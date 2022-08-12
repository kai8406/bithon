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

package org.bithon.agent.rpc.brpc.metrics;

import org.bithon.agent.rpc.brpc.BrpcMessageHeader;
import org.bithon.component.brpc.BrpcMethod;
import org.bithon.component.brpc.BrpcService;

import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/6/27 19:57
 */
@BrpcService
public interface IMetricCollector {

    @BrpcMethod(isOneway = true)
    void sendJvm(BrpcMessageHeader header, List<BrpcJvmMetricMessage> messages);

    @BrpcMethod(isOneway = true)
    void sendGenericMetrics(BrpcMessageHeader header, BrpcGenericMetricMessage message);

    @BrpcMethod(isOneway = true)
    void sendGenericMetricsV2(BrpcMessageHeader header, BrpcGenericMetricMessageV2 message);
}
