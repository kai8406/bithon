/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonTraceSpanSummary implements Serializable {

    private static final long serialVersionUID = -198231268;

    private Timestamp timestamp;
    private String    appname;
    private String    instancename;
    private String    name;
    private String    clazz;
    private String    method;
    private String    traceid;
    private String    spanid;
    private String    parentspanid;
    private String    kind;
    private Long      costtimems;
    private Long      starttimeus;
    private Long      endtimeus;
    private String    tags;
    private String    normalizedurl;
    private String    status;

    public BithonTraceSpanSummary() {}

    public BithonTraceSpanSummary(BithonTraceSpanSummary value) {
        this.timestamp = value.timestamp;
        this.appname = value.appname;
        this.instancename = value.instancename;
        this.name = value.name;
        this.clazz = value.clazz;
        this.method = value.method;
        this.traceid = value.traceid;
        this.spanid = value.spanid;
        this.parentspanid = value.parentspanid;
        this.kind = value.kind;
        this.costtimems = value.costtimems;
        this.starttimeus = value.starttimeus;
        this.endtimeus = value.endtimeus;
        this.tags = value.tags;
        this.normalizedurl = value.normalizedurl;
        this.status = value.status;
    }

    public BithonTraceSpanSummary(
        Timestamp timestamp,
        String    appname,
        String    instancename,
        String    name,
        String    clazz,
        String    method,
        String    traceid,
        String    spanid,
        String    parentspanid,
        String    kind,
        Long      costtimems,
        Long      starttimeus,
        Long      endtimeus,
        String    tags,
        String    normalizedurl,
        String    status
    ) {
        this.timestamp = timestamp;
        this.appname = appname;
        this.instancename = instancename;
        this.name = name;
        this.clazz = clazz;
        this.method = method;
        this.traceid = traceid;
        this.spanid = spanid;
        this.parentspanid = parentspanid;
        this.kind = kind;
        this.costtimems = costtimems;
        this.starttimeus = starttimeus;
        this.endtimeus = endtimeus;
        this.tags = tags;
        this.normalizedurl = normalizedurl;
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getAppname() {
        return this.appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getInstancename() {
        return this.instancename;
    }

    public void setInstancename(String instancename) {
        this.instancename = instancename;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTraceid() {
        return this.traceid;
    }

    public void setTraceid(String traceid) {
        this.traceid = traceid;
    }

    public String getSpanid() {
        return this.spanid;
    }

    public void setSpanid(String spanid) {
        this.spanid = spanid;
    }

    public String getParentspanid() {
        return this.parentspanid;
    }

    public void setParentspanid(String parentspanid) {
        this.parentspanid = parentspanid;
    }

    public String getKind() {
        return this.kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Long getCosttimems() {
        return this.costtimems;
    }

    public void setCosttimems(Long costtimems) {
        this.costtimems = costtimems;
    }

    public Long getStarttimeus() {
        return this.starttimeus;
    }

    public void setStarttimeus(Long starttimeus) {
        this.starttimeus = starttimeus;
    }

    public Long getEndtimeus() {
        return this.endtimeus;
    }

    public void setEndtimeus(Long endtimeus) {
        this.endtimeus = endtimeus;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getNormalizedurl() {
        return this.normalizedurl;
    }

    public void setNormalizedurl(String normalizedurl) {
        this.normalizedurl = normalizedurl;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BithonTraceSpanSummary (");

        sb.append(timestamp);
        sb.append(", ").append(appname);
        sb.append(", ").append(instancename);
        sb.append(", ").append(name);
        sb.append(", ").append(clazz);
        sb.append(", ").append(method);
        sb.append(", ").append(traceid);
        sb.append(", ").append(spanid);
        sb.append(", ").append(parentspanid);
        sb.append(", ").append(kind);
        sb.append(", ").append(costtimems);
        sb.append(", ").append(starttimeus);
        sb.append(", ").append(endtimeus);
        sb.append(", ").append(tags);
        sb.append(", ").append(normalizedurl);
        sb.append(", ").append(status);

        sb.append(")");
        return sb.toString();
    }
}