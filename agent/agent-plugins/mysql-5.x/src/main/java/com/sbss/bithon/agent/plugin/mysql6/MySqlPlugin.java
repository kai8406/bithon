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
public class MySqlPlugin extends AbstractPlugin {

    @Override
    public List<IPluginInstallationChecker> getCheckers() {
        return Collections.singletonList(
            hasClass("org.gjt.mm.mysql.Driver", true)
        );
    }

    @Override
    public List<InterceptorDescriptor> getInterceptors() {

        return Arrays.asList(
            // metrics
            forClass("com.mysql.jdbc.MysqlIO")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("sendCommand",
                                                                    "int",
                                                                    "java.lang.String",
                                                                    "com.mysql.jdbc.Buffer",
                                                                    "boolean",
                                                                    "java.lang.String",
                                                                    "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.MysqlIO$SendCommand"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("readAllResults",
                                                                    "com.mysql.jdbc.StatementImpl",
                                                                    "int", "int", "int", "boolean",
                                                                    "java.lang.String",
                                                                    "com.mysql.jdbc.Buffer",
                                                                    "boolean", "long",
                                                                    "[Lcom.mysql.jdbc.Field;")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.MysqlIO$ReadAllResults")
                ),


            // mysql5
            forClass("com.mysql.jdbc.PreparedStatement")
                .methods(
                    //
                    // metrics
                    //
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("execute")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.PreparedStatement$Execute"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeQuery")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.PreparedStatement$ExecuteQuery"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeUpdate")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.PreparedStatement$ExecuteUpdate"),

                    //
                    // trace
                    //
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("execute")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.PreparedStatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeQuery")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.PreparedStatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndNoArgs("executeUpdate")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.PreparedStatementTraceInterceptor")
                ),


            forClass("com.mysql.jdbc.StatementImpl")
                .methods(
                    //
                    // metrics
                    //
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeInternal",
                                                                    "java.lang.String",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.Statement$ExecuteInternal"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeQuery",
                                                                    "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.Statement$ExecuteQuery"),


                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdateInternal",
                                                                    "java.lang.String",
                                                                    "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.metrics.Statement$ExecuteUpdateInternal"),

                    //
                    // trace
                    //
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("execute",
                                                                    "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.StatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeQuery",
                                                                    "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.StatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdate",
                                                                    "java.lang.String", "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.StatementTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("executeUpdateInternal",
                                                                    "java.lang.String", "boolean",
                                                                    "boolean")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.StatementTraceInterceptor")
                ),

            //
            // trace
            //
            forClass("com.mysql.jdbc.ConnectionImpl")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement", "java.lang.String")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement", "java.lang.String", "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement", "java.lang.String", "[I")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String",
                                                                    "[Ljava.lang.String;")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String",
                                                                    "int",
                                                                    "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.ConnectionTraceInterceptor"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onMethodAndArgs("prepareStatement",
                                                                    "java.lang.String", "int", "int", "int")
                                                   .to("com.sbss.bithon.agent.plugin.mysql.trace.ConnectionTraceInterceptor")
                )
        );
    }
}
