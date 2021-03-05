/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.trace;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-03-05")
public class TraceMessage implements org.apache.thrift.TBase<TraceMessage, TraceMessage._Fields>, java.io.Serializable, Cloneable, Comparable<TraceMessage> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TraceMessage");

  private static final org.apache.thrift.protocol.TField APP_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("appName", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField ENV_FIELD_DESC = new org.apache.thrift.protocol.TField("env", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField HOST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("hostName", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField PORT_FIELD_DESC = new org.apache.thrift.protocol.TField("port", org.apache.thrift.protocol.TType.I32, (short)4);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short)5);
  private static final org.apache.thrift.protocol.TField SPANS_FIELD_DESC = new org.apache.thrift.protocol.TField("spans", org.apache.thrift.protocol.TType.LIST, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TraceMessageStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TraceMessageTupleSchemeFactory();

  public @org.apache.thrift.annotation.Nullable java.lang.String appName; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String env; // required
  public @org.apache.thrift.annotation.Nullable java.lang.String hostName; // required
  public int port; // required
  public long timestamp; // required
  public @org.apache.thrift.annotation.Nullable java.util.List<TraceSpan> spans; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    APP_NAME((short)1, "appName"),
    ENV((short)2, "env"),
    HOST_NAME((short)3, "hostName"),
    PORT((short)4, "port"),
    TIMESTAMP((short)5, "timestamp"),
    SPANS((short)6, "spans");

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
        case 5: // TIMESTAMP
          return TIMESTAMP;
        case 6: // SPANS
          return SPANS;
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
  private static final int __TIMESTAMP_ISSET_ID = 1;
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
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.SPANS, new org.apache.thrift.meta_data.FieldMetaData("spans", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TraceSpan.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TraceMessage.class, metaDataMap);
  }

  public TraceMessage() {
  }

  public TraceMessage(
    java.lang.String appName,
    java.lang.String env,
    java.lang.String hostName,
    int port,
    long timestamp,
    java.util.List<TraceSpan> spans)
  {
    this();
    this.appName = appName;
    this.env = env;
    this.hostName = hostName;
    this.port = port;
    setPortIsSet(true);
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    this.spans = spans;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TraceMessage(TraceMessage other) {
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
    this.timestamp = other.timestamp;
    if (other.isSetSpans()) {
      java.util.List<TraceSpan> __this__spans = new java.util.ArrayList<TraceSpan>(other.spans.size());
      for (TraceSpan other_element : other.spans) {
        __this__spans.add(new TraceSpan(other_element));
      }
      this.spans = __this__spans;
    }
  }

  public TraceMessage deepCopy() {
    return new TraceMessage(this);
  }

  @Override
  public void clear() {
    this.appName = null;
    this.env = null;
    this.hostName = null;
    setPortIsSet(false);
    this.port = 0;
    setTimestampIsSet(false);
    this.timestamp = 0;
    this.spans = null;
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.String getAppName() {
    return this.appName;
  }

  public TraceMessage setAppName(@org.apache.thrift.annotation.Nullable java.lang.String appName) {
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

  public TraceMessage setEnv(@org.apache.thrift.annotation.Nullable java.lang.String env) {
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

  public TraceMessage setHostName(@org.apache.thrift.annotation.Nullable java.lang.String hostName) {
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

  public TraceMessage setPort(int port) {
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

  public long getTimestamp() {
    return this.timestamp;
  }

  public TraceMessage setTimestamp(long timestamp) {
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

  public int getSpansSize() {
    return (this.spans == null) ? 0 : this.spans.size();
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.Iterator<TraceSpan> getSpansIterator() {
    return (this.spans == null) ? null : this.spans.iterator();
  }

  public void addToSpans(TraceSpan elem) {
    if (this.spans == null) {
      this.spans = new java.util.ArrayList<TraceSpan>();
    }
    this.spans.add(elem);
  }

  @org.apache.thrift.annotation.Nullable
  public java.util.List<TraceSpan> getSpans() {
    return this.spans;
  }

  public TraceMessage setSpans(@org.apache.thrift.annotation.Nullable java.util.List<TraceSpan> spans) {
    this.spans = spans;
    return this;
  }

  public void unsetSpans() {
    this.spans = null;
  }

  /** Returns true if field spans is set (has been assigned a value) and false otherwise */
  public boolean isSetSpans() {
    return this.spans != null;
  }

  public void setSpansIsSet(boolean value) {
    if (!value) {
      this.spans = null;
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

    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((java.lang.Long)value);
      }
      break;

    case SPANS:
      if (value == null) {
        unsetSpans();
      } else {
        setSpans((java.util.List<TraceSpan>)value);
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

    case TIMESTAMP:
      return getTimestamp();

    case SPANS:
      return getSpans();

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
    case TIMESTAMP:
      return isSetTimestamp();
    case SPANS:
      return isSetSpans();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TraceMessage)
      return this.equals((TraceMessage)that);
    return false;
  }

  public boolean equals(TraceMessage that) {
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

    boolean this_present_timestamp = true;
    boolean that_present_timestamp = true;
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (this.timestamp != that.timestamp)
        return false;
    }

    boolean this_present_spans = true && this.isSetSpans();
    boolean that_present_spans = true && that.isSetSpans();
    if (this_present_spans || that_present_spans) {
      if (!(this_present_spans && that_present_spans))
        return false;
      if (!this.spans.equals(that.spans))
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

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(timestamp);

    hashCode = hashCode * 8191 + ((isSetSpans()) ? 131071 : 524287);
    if (isSetSpans())
      hashCode = hashCode * 8191 + spans.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TraceMessage other) {
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
    lastComparison = java.lang.Boolean.valueOf(isSetSpans()).compareTo(other.isSetSpans());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSpans()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.spans, other.spans);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TraceMessage(");
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
    sb.append("timestamp:");
    sb.append(this.timestamp);
    first = false;
    if (!first) sb.append(", ");
    sb.append("spans:");
    if (this.spans == null) {
      sb.append("null");
    } else {
      sb.append(this.spans);
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

  private static class TraceMessageStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TraceMessageStandardScheme getScheme() {
      return new TraceMessageStandardScheme();
    }
  }

  private static class TraceMessageStandardScheme extends org.apache.thrift.scheme.StandardScheme<TraceMessage> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TraceMessage struct) throws org.apache.thrift.TException {
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
          case 5: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timestamp = iprot.readI64();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // SPANS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list10 = iprot.readListBegin();
                struct.spans = new java.util.ArrayList<TraceSpan>(_list10.size);
                @org.apache.thrift.annotation.Nullable TraceSpan _elem11;
                for (int _i12 = 0; _i12 < _list10.size; ++_i12)
                {
                  _elem11 = new TraceSpan();
                  _elem11.read(iprot);
                  struct.spans.add(_elem11);
                }
                iprot.readListEnd();
              }
              struct.setSpansIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TraceMessage struct) throws org.apache.thrift.TException {
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
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.timestamp);
      oprot.writeFieldEnd();
      if (struct.spans != null) {
        oprot.writeFieldBegin(SPANS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.spans.size()));
          for (TraceSpan _iter13 : struct.spans)
          {
            _iter13.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TraceMessageTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TraceMessageTupleScheme getScheme() {
      return new TraceMessageTupleScheme();
    }
  }

  private static class TraceMessageTupleScheme extends org.apache.thrift.scheme.TupleScheme<TraceMessage> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TraceMessage struct) throws org.apache.thrift.TException {
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
      if (struct.isSetTimestamp()) {
        optionals.set(4);
      }
      if (struct.isSetSpans()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
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
      if (struct.isSetTimestamp()) {
        oprot.writeI64(struct.timestamp);
      }
      if (struct.isSetSpans()) {
        {
          oprot.writeI32(struct.spans.size());
          for (TraceSpan _iter14 : struct.spans)
          {
            _iter14.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TraceMessage struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
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
        struct.timestamp = iprot.readI64();
        struct.setTimestampIsSet(true);
      }
      if (incoming.get(5)) {
        {
          org.apache.thrift.protocol.TList _list15 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.spans = new java.util.ArrayList<TraceSpan>(_list15.size);
          @org.apache.thrift.annotation.Nullable TraceSpan _elem16;
          for (int _i17 = 0; _i17 < _list15.size; ++_i17)
          {
            _elem16 = new TraceSpan();
            _elem16.read(iprot);
            struct.spans.add(_elem16);
          }
        }
        struct.setSpansIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

