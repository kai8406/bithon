package com.sbss.bithon.server.events.collector;

import com.sbss.bithon.agent.rpc.thrift.service.MessageHeader;
import com.sbss.bithon.agent.rpc.thrift.service.event.IEventCollector;
import com.sbss.bithon.agent.rpc.thrift.service.event.ThriftEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/14 3:59 下午
 */
@Slf4j
@Service
public class EventCollectorThriftImpl implements IEventCollector.Iface {

    private final EventsMessageHandler messageHandler;

    public EventCollectorThriftImpl(EventsMessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @Override
    public void sendEvent(MessageHeader header, ThriftEventMessage message) {
        EventMessage eventMessage = EventMessage.builder().appName(header.getAppName())
            .instanceName(header.getHostName())
            .timestamp(message.getTimestamp())
            .type(message.getEventType())
            .args(message.getArguments())
            .build();
        messageHandler.submit(header, eventMessage);
    }
}
