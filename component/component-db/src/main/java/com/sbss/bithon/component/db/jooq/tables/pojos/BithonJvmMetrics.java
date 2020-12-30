/*
 * This file is generated by jOOQ.
 */
package com.sbss.bithon.component.db.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonJvmMetrics implements Serializable {

    private static final long serialVersionUID = 1345324790;

    private Timestamp timestamp;
    private String    appname;
    private String    instancename;
    private Double    processcpuload;
    private Long      instanceuptime;
    private Long      instancestarttime;
    private Long      heap;
    private Long      heapinit;
    private Long      heapused;
    private Long      heapcommitted;
    private Long      peakthreads;
    private Long      daemonthreads;
    private Long      totalthreads;
    private Long      activethreads;

    public BithonJvmMetrics() {}

    public BithonJvmMetrics(BithonJvmMetrics value) {
        this.timestamp = value.timestamp;
        this.appname = value.appname;
        this.instancename = value.instancename;
        this.processcpuload = value.processcpuload;
        this.instanceuptime = value.instanceuptime;
        this.instancestarttime = value.instancestarttime;
        this.heap = value.heap;
        this.heapinit = value.heapinit;
        this.heapused = value.heapused;
        this.heapcommitted = value.heapcommitted;
        this.peakthreads = value.peakthreads;
        this.daemonthreads = value.daemonthreads;
        this.totalthreads = value.totalthreads;
        this.activethreads = value.activethreads;
    }

    public BithonJvmMetrics(
        Timestamp timestamp,
        String    appname,
        String    instancename,
        Double    processcpuload,
        Long      instanceuptime,
        Long      instancestarttime,
        Long      heap,
        Long      heapinit,
        Long      heapused,
        Long      heapcommitted,
        Long      peakthreads,
        Long      daemonthreads,
        Long      totalthreads,
        Long      activethreads
    ) {
        this.timestamp = timestamp;
        this.appname = appname;
        this.instancename = instancename;
        this.processcpuload = processcpuload;
        this.instanceuptime = instanceuptime;
        this.instancestarttime = instancestarttime;
        this.heap = heap;
        this.heapinit = heapinit;
        this.heapused = heapused;
        this.heapcommitted = heapcommitted;
        this.peakthreads = peakthreads;
        this.daemonthreads = daemonthreads;
        this.totalthreads = totalthreads;
        this.activethreads = activethreads;
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

    public Double getProcesscpuload() {
        return this.processcpuload;
    }

    public void setProcesscpuload(Double processcpuload) {
        this.processcpuload = processcpuload;
    }

    public Long getInstanceuptime() {
        return this.instanceuptime;
    }

    public void setInstanceuptime(Long instanceuptime) {
        this.instanceuptime = instanceuptime;
    }

    public Long getInstancestarttime() {
        return this.instancestarttime;
    }

    public void setInstancestarttime(Long instancestarttime) {
        this.instancestarttime = instancestarttime;
    }

    public Long getHeap() {
        return this.heap;
    }

    public void setHeap(Long heap) {
        this.heap = heap;
    }

    public Long getHeapinit() {
        return this.heapinit;
    }

    public void setHeapinit(Long heapinit) {
        this.heapinit = heapinit;
    }

    public Long getHeapused() {
        return this.heapused;
    }

    public void setHeapused(Long heapused) {
        this.heapused = heapused;
    }

    public Long getHeapcommitted() {
        return this.heapcommitted;
    }

    public void setHeapcommitted(Long heapcommitted) {
        this.heapcommitted = heapcommitted;
    }

    public Long getPeakthreads() {
        return this.peakthreads;
    }

    public void setPeakthreads(Long peakthreads) {
        this.peakthreads = peakthreads;
    }

    public Long getDaemonthreads() {
        return this.daemonthreads;
    }

    public void setDaemonthreads(Long daemonthreads) {
        this.daemonthreads = daemonthreads;
    }

    public Long getTotalthreads() {
        return this.totalthreads;
    }

    public void setTotalthreads(Long totalthreads) {
        this.totalthreads = totalthreads;
    }

    public Long getActivethreads() {
        return this.activethreads;
    }

    public void setActivethreads(Long activethreads) {
        this.activethreads = activethreads;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BithonJvmMetrics (");

        sb.append(timestamp);
        sb.append(", ").append(appname);
        sb.append(", ").append(instancename);
        sb.append(", ").append(processcpuload);
        sb.append(", ").append(instanceuptime);
        sb.append(", ").append(instancestarttime);
        sb.append(", ").append(heap);
        sb.append(", ").append(heapinit);
        sb.append(", ").append(heapused);
        sb.append(", ").append(heapcommitted);
        sb.append(", ").append(peakthreads);
        sb.append(", ").append(daemonthreads);
        sb.append(", ").append(totalthreads);
        sb.append(", ").append(activethreads);

        sb.append(")");
        return sb.toString();
    }
}
