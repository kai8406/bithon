package com.sbss.bithon.agent.dispatcher.thrift;

import com.sbss.bithon.agent.core.config.DispatcherConfig;
import com.sbss.bithon.agent.core.context.AgentContext;
import com.sbss.bithon.agent.core.context.AppInstance;
import com.sbss.bithon.agent.core.dispatcher.channel.IMessageChannel;
import com.sbss.bithon.agent.rpc.thrift.service.MessageHeader;
import com.sbss.bithon.agent.rpc.thrift.service.metric.IMetricCollector;
import org.apache.thrift.protocol.TProtocol;
import shaded.org.slf4j.Logger;
import shaded.org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/6 11:40 下午
 */
public class ThriftMetricsMessageChannel implements IMessageChannel {
    private static final int MAX_RETRY = 3;
    static Logger log = LoggerFactory.getLogger(ThriftMetricsMessageChannel.class);
    private final Map<String, Method> sendMethods = new HashMap<>();
    private final AbstractThriftClient<IMetricCollector.Client> client;
    private final DispatcherConfig dispatcherConfig;
    private final MessageHeader header;

    public ThriftMetricsMessageChannel(DispatcherConfig dispatcherConfig) {
        Method[] methods = IMetricCollector.Client.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterCount() != 2) {
                continue;
            }
            if (!method.getName().startsWith("send") || method.getName().startsWith("send_")) {
                continue;
            }
            Type[] paramTypes = method.getGenericParameterTypes();
            if (!paramTypes[0].equals(MessageHeader.class)) {
                continue;
            }

            Type metricMessageParamType = paramTypes[1];
            if (metricMessageParamType instanceof ParameterizedType) {
                Type messageType = ((ParameterizedType) metricMessageParamType).getActualTypeArguments()[0];
                if (messageType instanceof Class) {
                    sendMethods.put(((Class<?>) messageType).getName(), method);
                }
            }
        }

        this.client = new AbstractThriftClient<IMetricCollector.Client>("metric",
                                                                        dispatcherConfig.getServers(),
                                                                        dispatcherConfig.getClient().getTimeout()) {
            @Override
            protected IMetricCollector.Client createClient(TProtocol protocol) {
                return new IMetricCollector.Client(protocol);
            }
        };

        this.dispatcherConfig = dispatcherConfig;

        AppInstance appInstance = AgentContext.getInstance().getAppInstance();
        this.header = new MessageHeader();
        this.header.setAppName(appInstance.getAppName());
        this.header.setEnv(appInstance.getEnv());
        this.header.setInstanceName(appInstance.getHostIp() + ":" + appInstance.getPort());
        this.header.setHostIp(appInstance.getHostIp());
        this.header.setPort(appInstance.getPort());
        appInstance.addListener(port -> {
            this.header.setPort(appInstance.getPort());
            this.header.setInstanceName(appInstance.getHostIp() + ":" + appInstance.getPort());
        });
    }

    @Override
    public void sendMessage(Object message) {
        if (!(message instanceof List)) {
            return;
        }
        final String messageClass = ((List<?>) message).get(0).getClass().getName();

        // TODO: check timestamp first

        this.client.ensureClient((client) -> {
            Method method = sendMethods.get(messageClass);
            if (null == method) {
                log.error("No service method found for entity: " + messageClass);
                return null;
            }
            try {
                boolean isDebugOn = this.dispatcherConfig.getMessageDebug().getOrDefault(messageClass, false);
                if (isDebugOn) {
                    log.info("[Debugging] Sending Thrift Messages: {}", message);
                }
                return method.invoke(client, header, message);
            } catch (IllegalAccessException e) {
                log.warn("Failed to send metrics: []-[]", messageClass, method);
                return null;
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e.getTargetException());
            }
        }, MAX_RETRY);
    }
}
