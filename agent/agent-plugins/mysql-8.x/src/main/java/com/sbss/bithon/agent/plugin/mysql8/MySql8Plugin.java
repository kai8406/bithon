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

package com.sbss.bithon.agent.plugin.mysql8;

import com.sbss.bithon.agent.core.aop.descriptor.InterceptorDescriptor;
import com.sbss.bithon.agent.core.aop.descriptor.MethodPointCutDescriptorBuilder;
import com.sbss.bithon.agent.core.aop.precondition.IInterceptorPrecondition;
import com.sbss.bithon.agent.core.plugin.IPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sbss.bithon.agent.core.aop.descriptor.InterceptorDescriptorBuilder.forClass;

/**
 * @author frankchen
 */
public class MySql8Plugin implements IPlugin {
    static final String METHOD_EXECUTE = "execute";
    static final String METHOD_EXECUTE_UPDATE = "executeUpdate";
    static final String METHOD_EXECUTE_INTERNAL = "executeInternal";
    static final String METHOD_EXECUTE_UPDATE_INTERNAL = "executeUpdateInternal";
    static final String METHOD_SEND_COMMAND = "sendCommand";

    @Override
    public List<IInterceptorPrecondition> getPreconditions() {
        return Collections.singletonList(
            IInterceptorPrecondition.hasClass("com.mysql.cj.interceptors.QueryInterceptor", true)
        );
    }

    @Override
    public List<InterceptorDescriptor> getInterceptors() {

        return Arrays.asList(

            // mysql-connector 8
            forClass("com.mysql.cj.jdbc.ClientPreparedStatement")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("execute")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.PreparedStatementInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("executeQuery")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.PreparedStatementInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("executeUpdate")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.PreparedStatementInterceptor")
                ),

            //
            // IO
            //
            forClass("com.mysql.cj.protocol.a.NativeProtocol")
                .methods(
                    MethodPointCutDescriptorBuilder.build()

                                                   .onMethodAndArgs("sendCommand",
                                                                    "com.mysql.cj.protocol.Message",
                                                                    "boolean",
                                                                    "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.NativeProtocolInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("readAllResults",
                                                                    "int",
                                                                    "boolean",
                                                                    "com.mysql.cj.protocol.a.NativePacketPayload",
                                                                    "boolean",
                                                                    "com.mysql.cj.protocol.ColumnDefinition",
                                                                    "com.mysql.cj.protocol.ProtocolEntityFactory")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.NativeProtocolInterceptor")
                ),


            //
            // statement
            //
            forClass("com.mysql.cj.jdbc.StatementImpl")
                .methods(
                    MethodPointCutDescriptorBuilder.build()

                                                   .onMethodAndArgs("executeInternal",
                                                                    "java.lang.String", "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.StatementInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeQuery",
                                                                    "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.StatementInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdate",
                                                                    "java.lang.String",
                                                                    "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.StatementInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdateInternal",
                                                                    "java.lang.String",
                                                                    "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql8.StatementInterceptor"))
        );
    }
}
