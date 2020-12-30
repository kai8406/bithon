/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
/**
 * ***************** Thread Pool ******************************************
 */
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-03-03")
public class ThreadPoolMessage implements org.apache.thrift.TBase<ThreadPoolMessage, ThreadPoolMessage._Fields>, java.io.Serializable, Cloneable, Comparable<ThreadPoolMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThreadPoolMessage");

  private static final org.apache.thrift.protocol.TField APP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("appName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ENV_FIELD_DESC = new org.apache.thrift.protocol.TField("env", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField HOST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("hostName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("port", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField INTERVAL_FIELD_DESC = new org.apache.thrift.protocol.TField("interval", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short)6);
  private static final org.apache.thrift.protocol.TField POOLS_FIELD_DESC = new org.apache.thrift.protocol.TField("pools", org.apache.thrift.protocol.TType.LIST, (short)7);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ThreadPoolMessageStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ThreadPoolMessageTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String appName; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String env; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String hostName; // required
  public int port; // required
  public int interval; // required
  public long timestamp; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<ThreadPoolEntity> pools; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APP_NAME((short)1, "appName"),
    ENV((short)2, "env"),
    HOST_NAME((short)3, "hostName"),
    PORT((short)4, "port"),
    INTERVAL((short)5, "interval"),
    TIMESTAMP((short)6, "timestamp"),
    POOLS((short)7, "pools");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // APP_NAME
          return APP_NAME;
        case 2: // ENV
          return ENV;
        case 3: // HOST_NAME
          return HOST_NAME;
        case 4: // PORT
          return PORT;
        case 5: // INTERVAL
          return INTERVAL;
        case 6: // TIMESTAMP
          return TIMESTAMP;
        case 7: // POOLS
          return POOLS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __PORT_ISSET_ID = 0;
  private static final int __INTERVAL_ISSET_ID = 1;
  private static final int __TIMESTAMP_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.APP_NAME, new org.apache.thrift.meta_data.FieldMetaData("appName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.ENV, new org.apache.thrift.meta_data.FieldMetaData("env", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOST_NAME, new org.apache.thrift.meta_data.FieldMetaData("hostName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PORT, new org.apache.thrift.meta_data.FieldMetaData("port", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.INTERVAL, new org.apache.thrift.meta_data.FieldMetaData("interval", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.POOLS, new org.apache.thrift.meta_data.FieldMetaData("pools", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "ThreadPoolEntity"))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThreadPoolMessage.class, metaDataMap);
  }

  public ThreadPoolMessage() {
  }

  public ThreadPoolMessage(
    java.lang.String appName,
    java.lang.String env,
    java.lang.String hostName,
    int port,
    int interval,
    long timestamp,
    java.util.List<ThreadPoolEntity> pools)
  {
    this();
    this.appName = appName;
    this.env = env;
    this.hostName = hostName;
    this.port = port;
    setPortIsSet(true);
    this.interval = interval;
    setIntervalIsSet(true);
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    this.pools = pools;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThreadPoolMessage(ThreadPoolMessage other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetAppName()) {
      this.appName = other.appName;
    }
    if (other.isSetEnv()) {
      this.env = other.env;
    }
    if (other.isSetHostName()) {
      this.hostName = other.hostName;
    }
    this.port = other.port;
    this.interval = other.interval;
    this.timestamp = other.timestamp;
    if (other.isSetPools()) {
      java.util.List<ThreadPoolEntity> __this__pools = new java.util.ArrayList<ThreadPoolEntity>(other.pools.size());
      for (ThreadPoolEntity other_element : other.pools) {
        __this__pools.add(new ThreadPoolEntity(other_element));
      }
      this.pools = __this__pools;
    }
  }

  public ThreadPoolMessage deepCopy() {
    return new ThreadPoolMessage(this);
  }

  @Override
  public void clear() {
    this.appName = null;
    this.env = null;
    this.hostName = null;
    setPortIsSet(false);
    this.port = 0;
    setIntervalIsSet(false);
    this.interval = 0;
    setTimestampIsSet(false);
    this.timestamp = 0;
    this.pools = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getAppName() {
    return this.appName;
  }

  public ThreadPoolMessage setAppName(@org.apache.thrift.annotation.Nullable java.lang.String appName) {
    this.appName = appName;
    return this;
  }

  public void unsetAppName() {
    this.appName = null;
  }

  /** Returns true if field appName is set (has been assigned a value) and false otherwise */
  public boolean isSetAppName() {
    return this.appName != null;
  }

  public void setAppNameIsSet(boolean value) {
    if (!value) {
      this.appName = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getEnv() {
    return this.env;
  }

  public ThreadPoolMessage setEnv(@org.apache.thrift.annotation.Nullable java.lang.String env) {
    this.env = env;
    return this;
  }

  public void unsetEnv() {
    this.env = null;
  }

  /** Returns true if field env is set (has been assigned a value) and false otherwise */
  public boolean isSetEnv() {
    return this.env != null;
  }

  public void setEnvIsSet(boolean value) {
    if (!value) {
      this.env = null;
    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getHostName() {
    return this.hostName;
  }

  public ThreadPoolMessage setHostName(@org.apache.thrift.annotation.Nullable java.lang.String hostName) {
    this.hostName = hostName;
    return this;
  }

  public void unsetHostName() {
    this.hostName = null;
  }

  /** Returns true if field hostName is set (has been assigned a value) and false otherwise */
  public boolean isSetHostName() {
    return this.hostName != null;
  }

  public void setHostNameIsSet(boolean value) {
    if (!value) {
      this.hostName = null;
    }
  }

  public int getPort() {
    return this.port;
  }

  public ThreadPoolMessage setPort(int port) {
    this.port = port;
    setPortIsSet(true);
    return this;
  }

  public void unsetPort() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  /** Returns true if field port is set (has been assigned a value) and false otherwise */
  public boolean isSetPort() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PORT_ISSET_ID);
  }

  public void setPortIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PORT_ISSET_ID, value);
  }

  public int getInterval() {
    return this.interval;
  }

  public ThreadPoolMessage setInterval(int interval) {
    this.interval = interval;
    setIntervalIsSet(true);
    return this;
  }

  public void unsetInterval() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __INTERVAL_ISSET_ID);
  }

  /** Returns true if field interval is set (has been assigned a value) and false otherwise */
  public boolean isSetInterval() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __INTERVAL_ISSET_ID);
  }

  public void setIntervalIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __INTERVAL_ISSET_ID, value);
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public ThreadPoolMessage setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    return this;
  }

  public void unsetTimestamp() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  /** Returns true if field timestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  public void setTimestampIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TIMESTAMP_ISSET_ID, value);
  }

  public int getPoolsSize() {
    return (this.pools == null) ? 0 : this.pools.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<ThreadPoolEntity> getPoolsIterator() {
    return (this.pools == null) ? null : this.pools.iterator();
  }

  public void addToPools(ThreadPoolEntity elem) {
    if (this.pools == null) {
      this.pools = new java.util.ArrayList<ThreadPoolEntity>();
    }
    this.pools.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<ThreadPoolEntity> getPools() {
    return this.pools;
  }

  public ThreadPoolMessage setPools(@org.apache.thrift.annotation.Nullable java.util.List<ThreadPoolEntity> pools) {
    this.pools = pools;
    return this;
  }

  public void unsetPools() {
    this.pools = null;
  }

  /** Returns true if field pools is set (has been assigned a value) and false otherwise */
  public boolean isSetPools() {
    return this.pools != null;
  }

  public void setPoolsIsSet(boolean value) {
    if (!value) {
      this.pools = null;
    }
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case APP_NAME:
      if (value == null) {
        unsetAppName();
      } else {
        setAppName((java.lang.String)value);
      }
      break;

    case ENV:
      if (value == null) {
        unsetEnv();
      } else {
        setEnv((java.lang.String)value);
      }
      break;

    case HOST_NAME:
      if (value == null) {
        unsetHostName();
      } else {
        setHostName((java.lang.String)value);
      }
      break;

    case PORT:
      if (value == null) {
        unsetPort();
      } else {
        setPort((java.lang.Integer)value);
      }
      break;

    case INTERVAL:
      if (value == null) {
        unsetInterval();
      } else {
        setInterval((java.lang.Integer)value);
      }
      break;

    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((java.lang.Long)value);
      }
      break;

    case POOLS:
      if (value == null) {
        unsetPools();
      } else {
        setPools((java.util.List<ThreadPoolEntity>)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case APP_NAME:
      return getAppName();

    case ENV:
      return getEnv();

    case HOST_NAME:
      return getHostName();

    case PORT:
      return getPort();

    case INTERVAL:
      return getInterval();

    case TIMESTAMP:
      return getTimestamp();

    case POOLS:
      return getPools();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case APP_NAME:
      return isSetAppName();
    case ENV:
      return isSetEnv();
    case HOST_NAME:
      return isSetHostName();
    case PORT:
      return isSetPort();
    case INTERVAL:
      return isSetInterval();
    case TIMESTAMP:
      return isSetTimestamp();
    case POOLS:
      return isSetPools();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ThreadPoolMessage)
      return this.equals((ThreadPoolMessage)that);
    return false;
  }

  public boolean equals(ThreadPoolMessage that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_appName = true && this.isSetAppName();
    boolean that_present_appName = true && that.isSetAppName();
    if (this_present_appName || that_present_appName) {
      if (!(this_present_appName && that_present_appName))
        return false;
      if (!this.appName.equals(that.appName))
        return false;
    }

    boolean this_present_env = true && this.isSetEnv();
    boolean that_present_env = true && that.isSetEnv();
    if (this_present_env || that_present_env) {
      if (!(this_present_env && that_present_env))
        return false;
      if (!this.env.equals(that.env))
        return false;
    }

    boolean this_present_hostName = true && this.isSetHostName();
    boolean that_present_hostName = true && that.isSetHostName();
    if (this_present_hostName || that_present_hostName) {
      if (!(this_present_hostName && that_present_hostName))
        return false;
      if (!this.hostName.equals(that.hostName))
        return false;
    }

    boolean this_present_port = true;
    boolean that_present_port = true;
    if (this_present_port || that_present_port) {
      if (!(this_present_port && that_present_port))
        return false;
      if (this.port != that.port)
        return false;
    }

    boolean this_present_interval = true;
    boolean that_present_interval = true;
    if (this_present_interval || that_present_interval) {
      if (!(this_present_interval && that_present_interval))
        return false;
      if (this.interval != that.interval)
        return false;
    }

    boolean this_present_timestamp = true;
    boolean that_present_timestamp = true;
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (this.timestamp != that.timestamp)
        return false;
    }

    boolean this_present_pools = true && this.isSetPools();
    boolean that_present_pools = true && that.isSetPools();
    if (this_present_pools || that_present_pools) {
      if (!(this_present_pools && that_present_pools))
        return false;
      if (!this.pools.equals(that.pools))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetAppName()) ? 131071 : 524287);
    if (isSetAppName())
      hashCode = hashCode * 8191 + appName.hashCode();

    hashCode = hashCode * 8191 + ((isSetEnv()) ? 131071 : 524287);
    if (isSetEnv())
      hashCode = hashCode * 8191 + env.hashCode();

    hashCode = hashCode * 8191 + ((isSetHostName()) ? 131071 : 524287);
    if (isSetHostName())
      hashCode = hashCode * 8191 + hostName.hashCode();

    hashCode = hashCode * 8191 + port;

    hashCode = hashCode * 8191 + interval;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(timestamp);

    hashCode = hashCode * 8191 + ((isSetPools()) ? 131071 : 524287);
    if (isSetPools())
      hashCode = hashCode * 8191 + pools.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(ThreadPoolMessage other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetAppName()).compareTo(other.isSetAppName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAppName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.appName, other.appName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetEnv()).compareTo(other.isSetEnv());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEnv()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.env, other.env);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetHostName()).compareTo(other.isSetHostName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHostName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.hostName, other.hostName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPort()).compareTo(other.isSetPort());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPort()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.port, other.port);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetInterval()).compareTo(other.isSetInterval());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInterval()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.interval, other.interval);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTimestamp()).compareTo(other.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, other.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPools()).compareTo(other.isSetPools());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPools()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.pools, other.pools);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ThreadPoolMessage(");
    boolean first = true;

    sb.append("appName:");
    if (this.appName == null) {
      sb.append("null");
    } else {
      sb.append(this.appName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("env:");
    if (this.env == null) {
      sb.append("null");
    } else {
      sb.append(this.env);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("hostName:");
    if (this.hostName == null) {
      sb.append("null");
    } else {
      sb.append(this.hostName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("port:");
    sb.append(this.port);
    first = false;
    if (!first) sb.append(", ");
    sb.append("interval:");
    sb.append(this.interval);
    first = false;
    if (!first) sb.append(", ");
    sb.append("timestamp:");
    sb.append(this.timestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("pools:");
    if (this.pools == null) {
      sb.append("null");
    } else {
      sb.append(this.pools);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class ThreadPoolMessageStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ThreadPoolMessageStandardScheme getScheme() {
      return new ThreadPoolMessageStandardScheme();
    }
  }

  private static class ThreadPoolMessageStandardScheme extends org.apache.thrift.scheme.StandardScheme<ThreadPoolMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThreadPoolMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // APP_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.appName = iprot.readString();
              struct.setAppNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ENV
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.env = iprot.readString();
              struct.setEnvIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // HOST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.hostName = iprot.readString();
              struct.setHostNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // PORT
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.port = iprot.readI32();
              struct.setPortIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // INTERVAL
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.interval = iprot.readI32();
              struct.setIntervalIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timestamp = iprot.readI64();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // POOLS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list16 = iprot.readListBegin();
                struct.pools = new java.util.ArrayList<ThreadPoolEntity>(_list16.size);
                @org.apache.thrift.annotation.Nullable ThreadPoolEntity _elem17;
                for (int _i18 = 0; _i18 < _list16.size; ++_i18)
                {
                  _elem17 = new ThreadPoolEntity();
                  _elem17.read(iprot);
                  struct.pools.add(_elem17);
                }
                iprot.readListEnd();
              }
              struct.setPoolsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThreadPoolMessage struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.appName != null) {
        oprot.writeFieldBegin(APP_NAME_FIELD_DESC);
        oprot.writeString(struct.appName);
        oprot.writeFieldEnd();
      }
      if (struct.env != null) {
        oprot.writeFieldBegin(ENV_FIELD_DESC);
        oprot.writeString(struct.env);
        oprot.writeFieldEnd();
      }
      if (struct.hostName != null) {
        oprot.writeFieldBegin(HOST_NAME_FIELD_DESC);
        oprot.writeString(struct.hostName);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PORT_FIELD_DESC);
      oprot.writeI32(struct.port);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(INTERVAL_FIELD_DESC);
      oprot.writeI32(struct.interval);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.timestamp);
      oprot.writeFieldEnd();
      if (struct.pools != null) {
        oprot.writeFieldBegin(POOLS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.pools.size()));
          for (ThreadPoolEntity _iter19 : struct.pools)
          {
            _iter19.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThreadPoolMessageTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ThreadPoolMessageTupleScheme getScheme() {
      return new ThreadPoolMessageTupleScheme();
    }
  }

  private static class ThreadPoolMessageTupleScheme extends org.apache.thrift.scheme.TupleScheme<ThreadPoolMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThreadPoolMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAppName()) {
        optionals.set(0);
      }
      if (struct.isSetEnv()) {
        optionals.set(1);
      }
      if (struct.isSetHostName()) {
        optionals.set(2);
      }
      if (struct.isSetPort()) {
        optionals.set(3);
      }
      if (struct.isSetInterval()) {
        optionals.set(4);
      }
      if (struct.isSetTimestamp()) {
        optionals.set(5);
      }
      if (struct.isSetPools()) {
        optionals.set(6);
      }
      oprot.writeBitSet(optionals, 7);
      if (struct.isSetAppName()) {
        oprot.writeString(struct.appName);
      }
      if (struct.isSetEnv()) {
        oprot.writeString(struct.env);
      }
      if (struct.isSetHostName()) {
        oprot.writeString(struct.hostName);
      }
      if (struct.isSetPort()) {
        oprot.writeI32(struct.port);
      }
      if (struct.isSetInterval()) {
        oprot.writeI32(struct.interval);
      }
      if (struct.isSetTimestamp()) {
        oprot.writeI64(struct.timestamp);
      }
      if (struct.isSetPools()) {
        {
          oprot.writeI32(struct.pools.size());
          for (ThreadPoolEntity _iter20 : struct.pools)
          {
            _iter20.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThreadPoolMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(7);
      if (incoming.get(0)) {
        struct.appName = iprot.readString();
        struct.setAppNameIsSet(true);
      }
      if (incoming.get(1)) {
        struct.env = iprot.readString();
        struct.setEnvIsSet(true);
      }
      if (incoming.get(2)) {
        struct.hostName = iprot.readString();
        struct.setHostNameIsSet(true);
      }
      if (incoming.get(3)) {
        struct.port = iprot.readI32();
        struct.setPortIsSet(true);
      }
      if (incoming.get(4)) {
        struct.interval = iprot.readI32();
        struct.setIntervalIsSet(true);
      }
      if (incoming.get(5)) {
        struct.timestamp = iprot.readI64();
        struct.setTimestampIsSet(true);
      }
      if (incoming.get(6)) {
        {
          org.apache.thrift.protocol.TList _list21 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.pools = new java.util.ArrayList<ThreadPoolEntity>(_list21.size);
          @org.apache.thrift.annotation.Nullable ThreadPoolEntity _elem22;
          for (int _i23 = 0; _i23 < _list21.size; ++_i23)
          {
            _elem22 = new ThreadPoolEntity();
            _elem22.read(iprot);
            struct.pools.add(_elem22);
          }
        }
        struct.setPoolsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

