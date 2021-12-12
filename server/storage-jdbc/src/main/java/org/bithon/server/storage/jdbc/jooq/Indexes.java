/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.jooq;


import org.bithon.server.storage.jdbc.jooq.tables.BithonAgentSetting;
import org.bithon.server.storage.jdbc.jooq.tables.BithonApplicationInstance;
import org.bithon.server.storage.jdbc.jooq.tables.BithonEvent;
import org.bithon.server.storage.jdbc.jooq.tables.BithonTraceMapping;
import org.bithon.server.storage.jdbc.jooq.tables.BithonTraceSpan;
import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code></code> schema.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index BITHON_AGENT_SETTING_KEY_APPNAME = Indexes0.BITHON_AGENT_SETTING_KEY_APPNAME;
    public static final Index BITHON_APPLICATION_INSTANCE_IDX_APP_INSTANCE_NAME = Indexes0.BITHON_APPLICATION_INSTANCE_IDX_APP_INSTANCE_NAME;
    public static final Index BITHON_APPLICATION_INSTANCE_IDX_APP_INSTANCE_TIMESTAMP = Indexes0.BITHON_APPLICATION_INSTANCE_IDX_APP_INSTANCE_TIMESTAMP;
    public static final Index BITHON_EVENT_IDX_EVENT_APPNAME = Indexes0.BITHON_EVENT_IDX_EVENT_APPNAME;
    public static final Index BITHON_EVENT_IDX_EVENT_INSTANCENAME = Indexes0.BITHON_EVENT_IDX_EVENT_INSTANCENAME;
    public static final Index BITHON_EVENT_IDX_EVENT_TIMESTAMP = Indexes0.BITHON_EVENT_IDX_EVENT_TIMESTAMP;
    public static final Index BITHON_EVENT_IDX_EVENT_TYPE = Indexes0.BITHON_EVENT_IDX_EVENT_TYPE;
    public static final Index BITHON_TRACE_MAPPING_IDX_TRACE_MAPPING_ID = Indexes0.BITHON_TRACE_MAPPING_IDX_TRACE_MAPPING_ID;
    public static final Index BITHON_TRACE_SPAN_IDX_APP_NAME = Indexes0.BITHON_TRACE_SPAN_IDX_APP_NAME;
    public static final Index BITHON_TRACE_SPAN_IDX_INSTANCENAME = Indexes0.BITHON_TRACE_SPAN_IDX_INSTANCENAME;
    public static final Index BITHON_TRACE_SPAN_IDX_KEY = Indexes0.BITHON_TRACE_SPAN_IDX_KEY;
    public static final Index BITHON_TRACE_SPAN_IDX_PARENTSPANID = Indexes0.BITHON_TRACE_SPAN_IDX_PARENTSPANID;
    public static final Index BITHON_TRACE_SPAN_IDX_START_TIME = Indexes0.BITHON_TRACE_SPAN_IDX_START_TIME;
    public static final Index BITHON_TRACE_SPAN_IDX_TIMESTAMP = Indexes0.BITHON_TRACE_SPAN_IDX_TIMESTAMP;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index BITHON_AGENT_SETTING_KEY_APPNAME = Internal.createIndex("key_appName", BithonAgentSetting.BITHON_AGENT_SETTING, new OrderField[] { BithonAgentSetting.BITHON_AGENT_SETTING.APPNAME, BithonAgentSetting.BITHON_AGENT_SETTING.SETTINGNAME }, true);
        public static Index BITHON_APPLICATION_INSTANCE_IDX_APP_INSTANCE_NAME = Internal.createIndex("idx_app_instance_name", BithonApplicationInstance.BITHON_APPLICATION_INSTANCE, new OrderField[] { BithonApplicationInstance.BITHON_APPLICATION_INSTANCE.APPNAME }, false);
        public static Index BITHON_APPLICATION_INSTANCE_IDX_APP_INSTANCE_TIMESTAMP = Internal.createIndex("idx_app_instance_timestamp", BithonApplicationInstance.BITHON_APPLICATION_INSTANCE, new OrderField[] { BithonApplicationInstance.BITHON_APPLICATION_INSTANCE.TIMESTAMP }, false);
        public static Index BITHON_EVENT_IDX_EVENT_APPNAME = Internal.createIndex("idx_event_appName", BithonEvent.BITHON_EVENT, new OrderField[] { BithonEvent.BITHON_EVENT.APPNAME }, false);
        public static Index BITHON_EVENT_IDX_EVENT_INSTANCENAME = Internal.createIndex("idx_event_instanceName", BithonEvent.BITHON_EVENT, new OrderField[] { BithonEvent.BITHON_EVENT.INSTANCENAME }, false);
        public static Index BITHON_EVENT_IDX_EVENT_TIMESTAMP = Internal.createIndex("idx_event_timestamp", BithonEvent.BITHON_EVENT, new OrderField[] { BithonEvent.BITHON_EVENT.TIMESTAMP }, false);
        public static Index BITHON_EVENT_IDX_EVENT_TYPE = Internal.createIndex("idx_event_type", BithonEvent.BITHON_EVENT, new OrderField[] { BithonEvent.BITHON_EVENT.TYPE }, false);
        public static Index BITHON_TRACE_MAPPING_IDX_TRACE_MAPPING_ID = Internal.createIndex("idx_trace_mapping_id", BithonTraceMapping.BITHON_TRACE_MAPPING, new OrderField[] { BithonTraceMapping.BITHON_TRACE_MAPPING.USER_TX_ID, BithonTraceMapping.BITHON_TRACE_MAPPING.TRACE_ID }, false);
        public static Index BITHON_TRACE_SPAN_IDX_APP_NAME = Internal.createIndex("idx_app_name", BithonTraceSpan.BITHON_TRACE_SPAN, new OrderField[] { BithonTraceSpan.BITHON_TRACE_SPAN.APPNAME }, false);
        public static Index BITHON_TRACE_SPAN_IDX_INSTANCENAME = Internal.createIndex("idx_instanceName", BithonTraceSpan.BITHON_TRACE_SPAN, new OrderField[] { BithonTraceSpan.BITHON_TRACE_SPAN.INSTANCENAME }, false);
        public static Index BITHON_TRACE_SPAN_IDX_KEY = Internal.createIndex("idx_key", BithonTraceSpan.BITHON_TRACE_SPAN, new OrderField[] { BithonTraceSpan.BITHON_TRACE_SPAN.TRACEID, BithonTraceSpan.BITHON_TRACE_SPAN.SPANID }, true);
        public static Index BITHON_TRACE_SPAN_IDX_PARENTSPANID = Internal.createIndex("idx_parentSpanId", BithonTraceSpan.BITHON_TRACE_SPAN, new OrderField[] { BithonTraceSpan.BITHON_TRACE_SPAN.PARENTSPANID }, false);
        public static Index BITHON_TRACE_SPAN_IDX_START_TIME = Internal.createIndex("idx_start_time", BithonTraceSpan.BITHON_TRACE_SPAN, new OrderField[] { BithonTraceSpan.BITHON_TRACE_SPAN.STARTTIMEUS }, false);
        public static Index BITHON_TRACE_SPAN_IDX_TIMESTAMP = Internal.createIndex("idx_timestamp", BithonTraceSpan.BITHON_TRACE_SPAN, new OrderField[] { BithonTraceSpan.BITHON_TRACE_SPAN.TIMESTAMP }, false);
    }
}
