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

package org.bithon.component.brpc;

import org.bithon.component.brpc.message.serializer.Serializer;
import shaded.io.netty.util.internal.StringUtil;

import java.lang.reflect.Method;

/**
 * @author Frank Chen
 * @date 20/10/21 9:20 pm
 */
public class ServiceConfiguration {
    private final String serviceName;
    private final String methodName;
    private final boolean isOneway;
    private final Serializer serializer;

    public ServiceConfiguration(String serviceName, String methodName, boolean isOneway, Serializer serializer) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.isOneway = isOneway;
        this.serializer = serializer;
    }

    public static ServiceConfiguration getServiceConfiguration(Method method) {
        ServiceConfig serviceConfig = method.getDeclaringClass().getAnnotation(ServiceConfig.class);
        ServiceConfig methodConfig = method.getAnnotation(ServiceConfig.class);

        String methodName;
        boolean isOneway;
        Serializer serializer;

        if (methodConfig != null && !StringUtil.isNullOrEmpty(methodConfig.name())) {
            methodName = methodConfig.name();
        } else {
            methodName = method.getName();
        }

        if (methodConfig != null) {
            isOneway = methodConfig.isOneway();
        } else {
            isOneway = serviceConfig != null && serviceConfig.isOneway();
        }

        if (methodConfig != null) {
            serializer = methodConfig.serializer();
        } else {
            serializer = serviceConfig == null ? Serializer.BINARY : serviceConfig.serializer();
        }

        String serviceName = serviceConfig != null && !StringUtil.isNullOrEmpty(serviceConfig.name())
                             ? serviceConfig.name()
                             : method.getDeclaringClass().getName();

        return new ServiceConfiguration(serviceName, methodName, isOneway, serializer);
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public boolean isOneway() {
        return isOneway;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getServiceName() {
        return serviceName;
    }
}
