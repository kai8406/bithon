package com.sbss.bithon.agent.plugin.mysql6;

import com.sbss.bithon.agent.core.plugin.AbstractPlugin;
import com.sbss.bithon.agent.core.plugin.descriptor.InterceptorDescriptor;
import com.sbss.bithon.agent.core.plugin.descriptor.MethodPointCutDescriptorBuilder;
import com.sbss.bithon.agent.core.plugin.precondition.IPluginInstallationChecker;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.sbss.bithon.agent.core.plugin.descriptor.InterceptorDescriptorBuilder.forClass;
import static com.sbss.bithon.agent.core.plugin.precondition.IPluginInstallationChecker.hasClass;

/**
 * @author frankchen
 */
public class MySql6Plugin extends AbstractPlugin {

    @Override
    public List<IPluginInstallationChecker> getCheckers() {
        return Collections.singletonList(
            hasClass("com.mysql.cj.x.package-info", true)
        );
    }

    @Override
    public List<InterceptorDescriptor> getInterceptors() {

        return Arrays.asList(
            //
            // IO
            //
            forClass("com.mysql.cj.mysqla.io.MysqlaProtocol")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("sendCommand")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.MysqlaProtocol$SendCommand")
                ),
            forClass("com.mysql.cj.jdbc.MysqlIO")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("readAllResults",
                                                                    "com.mysql.jdbc.StatementImpl",
                                                                    "int", "int", "int", "boolean",
                                                                    "java.lang.String",
                                                                    "com.mysql.jdbc.Buffer",
                                                                    "boolean", "long",
                                                                    "[Lcom.mysql.jdbc.Field;")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.MysqlIO$ReadAllResults")
                ),

            // mysql6
            forClass("com.mysql.cj.jdbc.PreparedStatement")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("execute")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.PreparedStatement$Execute"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeQuery")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.PreparedStatement$ExecuteQuery"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeUpdate")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.PreparedStatement$ExecuteUpdate"),

                    //
                    // trace
                    //
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("execute")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.PreparedStatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeQuery")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.PreparedStatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeUpdate")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.PreparedStatementTraceInterceptor")
                ),

            //mysql6
            forClass("com.mysql.cj.jdbc.StatementImpl")
                .methods(
                    //
                    // metrics
                    //
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeInternal",
                                                                    "java.lang.String",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.Statement$ExecuteInternal"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeQuery",
                                                                    "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.Statement$ExecuteQuery"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdateInternal",
                                                                    "java.lang.String",
                                                                    "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.metrics.Statement$ExecuteUpdateInternal"),

                    //
                    // trace
                    //
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("execute", "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.StatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeInternal",
                                                                    "java.lang.String",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.StatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeQuery",
                                                                    "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.StatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdate",
                                                                    "java.lang.String",
                                                                    "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.StatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdateInternal",
                                                                    "java.lang.String",
                                                                    "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.StatementTraceInterceptor")
                ),

            forClass("com.mysql.cj.jdbc.ConnectionImpl")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String", "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String", "[I")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String", "[Ljava.lang.String;")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String", "int", "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String", "int", "int", "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql6.trace.ConnectionTraceInterceptor")
                )
        );
    }
}
