package com.sbss.bithon.collector.events.storage;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/14 4:17 下午
 */
public interface IEventStorage {
    IEventWriter createWriter();
    IEventReader createReader();
}
