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
public class BithonMetaSchema implements Serializable {

    private static final long serialVersionUID = 1562707382;

    private Timestamp timestamp;
    private String    name;
    private String    schema;

    public BithonMetaSchema() {}

    public BithonMetaSchema(BithonMetaSchema value) {
        this.timestamp = value.timestamp;
        this.name = value.name;
        this.schema = value.schema;
    }

    public BithonMetaSchema(
        Timestamp timestamp,
        String    name,
        String    schema
    ) {
        this.timestamp = timestamp;
        this.name = name;
        this.schema = schema;
    }

    public Timestamp getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchema() {
        return this.schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BithonMetaSchema (");

        sb.append(timestamp);
        sb.append(", ").append(name);
        sb.append(", ").append(schema);

        sb.append(")");
        return sb.toString();
    }
}