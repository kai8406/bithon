/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-03-05")
public class InstanceTimeEntity implements org.apache.thrift.TBase<InstanceTimeEntity, InstanceTimeEntity._Fields>, java.io.Serializable, Cloneable, Comparable<InstanceTimeEntity> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("InstanceTimeEntity");

  private static final org.apache.thrift.protocol.TField INSTANCE_UP_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("instanceUpTime", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField INSTANCE_START_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("instanceStartTime", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new InstanceTimeEntityStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new InstanceTimeEntityTupleSchemeFactory();

  public long instanceUpTime; // required
  public long instanceStartTime; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    INSTANCE_UP_TIME((short)1, "instanceUpTime"),
    INSTANCE_START_TIME((short)2, "instanceStartTime");

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
        case 1: // INSTANCE_UP_TIME
          return INSTANCE_UP_TIME;
        case 2: // INSTANCE_START_TIME
          return INSTANCE_START_TIME;
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
  private static final int __INSTANCEUPTIME_ISSET_ID = 0;
  private static final int __INSTANCESTARTTIME_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.INSTANCE_UP_TIME, new org.apache.thrift.meta_data.FieldMetaData("instanceUpTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.INSTANCE_START_TIME, new org.apache.thrift.meta_data.FieldMetaData("instanceStartTime", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(InstanceTimeEntity.class, metaDataMap);
  }

  public InstanceTimeEntity() {
  }

  public InstanceTimeEntity(
    long instanceUpTime,
    long instanceStartTime)
  {
    this();
    this.instanceUpTime = instanceUpTime;
    setInstanceUpTimeIsSet(true);
    this.instanceStartTime = instanceStartTime;
    setInstanceStartTimeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public InstanceTimeEntity(InstanceTimeEntity other) {
    __isset_bitfield = other.__isset_bitfield;
    this.instanceUpTime = other.instanceUpTime;
    this.instanceStartTime = other.instanceStartTime;
  }

  public InstanceTimeEntity deepCopy() {
    return new InstanceTimeEntity(this);
  }

  @Override
  public void clear() {
    setInstanceUpTimeIsSet(false);
    this.instanceUpTime = 0;
    setInstanceStartTimeIsSet(false);
    this.instanceStartTime = 0;
  }

  public long getInstanceUpTime() {
    return this.instanceUpTime;
  }

  public InstanceTimeEntity setInstanceUpTime(long instanceUpTime) {
    this.instanceUpTime = instanceUpTime;
    setInstanceUpTimeIsSet(true);
    return this;
  }

  public void unsetInstanceUpTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __INSTANCEUPTIME_ISSET_ID);
  }

  /** Returns true if field instanceUpTime is set (has been assigned a value) and false otherwise */
  public boolean isSetInstanceUpTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __INSTANCEUPTIME_ISSET_ID);
  }

  public void setInstanceUpTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __INSTANCEUPTIME_ISSET_ID, value);
  }

  public long getInstanceStartTime() {
    return this.instanceStartTime;
  }

  public InstanceTimeEntity setInstanceStartTime(long instanceStartTime) {
    this.instanceStartTime = instanceStartTime;
    setInstanceStartTimeIsSet(true);
    return this;
  }

  public void unsetInstanceStartTime() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __INSTANCESTARTTIME_ISSET_ID);
  }

  /** Returns true if field instanceStartTime is set (has been assigned a value) and false otherwise */
  public boolean isSetInstanceStartTime() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __INSTANCESTARTTIME_ISSET_ID);
  }

  public void setInstanceStartTimeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __INSTANCESTARTTIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case INSTANCE_UP_TIME:
      if (value == null) {
        unsetInstanceUpTime();
      } else {
        setInstanceUpTime((java.lang.Long)value);
      }
      break;

    case INSTANCE_START_TIME:
      if (value == null) {
        unsetInstanceStartTime();
      } else {
        setInstanceStartTime((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case INSTANCE_UP_TIME:
      return getInstanceUpTime();

    case INSTANCE_START_TIME:
      return getInstanceStartTime();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case INSTANCE_UP_TIME:
      return isSetInstanceUpTime();
    case INSTANCE_START_TIME:
      return isSetInstanceStartTime();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof InstanceTimeEntity)
      return this.equals((InstanceTimeEntity)that);
    return false;
  }

  public boolean equals(InstanceTimeEntity that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_instanceUpTime = true;
    boolean that_present_instanceUpTime = true;
    if (this_present_instanceUpTime || that_present_instanceUpTime) {
      if (!(this_present_instanceUpTime && that_present_instanceUpTime))
        return false;
      if (this.instanceUpTime != that.instanceUpTime)
        return false;
    }

    boolean this_present_instanceStartTime = true;
    boolean that_present_instanceStartTime = true;
    if (this_present_instanceStartTime || that_present_instanceStartTime) {
      if (!(this_present_instanceStartTime && that_present_instanceStartTime))
        return false;
      if (this.instanceStartTime != that.instanceStartTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(instanceUpTime);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(instanceStartTime);

    return hashCode;
  }

  @Override
  public int compareTo(InstanceTimeEntity other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetInstanceUpTime()).compareTo(other.isSetInstanceUpTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInstanceUpTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.instanceUpTime, other.instanceUpTime);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetInstanceStartTime()).compareTo(other.isSetInstanceStartTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetInstanceStartTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.instanceStartTime, other.instanceStartTime);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("InstanceTimeEntity(");
    boolean first = true;

    sb.append("instanceUpTime:");
    sb.append(this.instanceUpTime);
    first = false;
    if (!first) sb.append(", ");
    sb.append("instanceStartTime:");
    sb.append(this.instanceStartTime);
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

  private static class InstanceTimeEntityStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public InstanceTimeEntityStandardScheme getScheme() {
      return new InstanceTimeEntityStandardScheme();
    }
  }

  private static class InstanceTimeEntityStandardScheme extends org.apache.thrift.scheme.StandardScheme<InstanceTimeEntity> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, InstanceTimeEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // INSTANCE_UP_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.instanceUpTime = iprot.readI64();
              struct.setInstanceUpTimeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // INSTANCE_START_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.instanceStartTime = iprot.readI64();
              struct.setInstanceStartTimeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, InstanceTimeEntity struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(INSTANCE_UP_TIME_FIELD_DESC);
      oprot.writeI64(struct.instanceUpTime);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(INSTANCE_START_TIME_FIELD_DESC);
      oprot.writeI64(struct.instanceStartTime);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class InstanceTimeEntityTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public InstanceTimeEntityTupleScheme getScheme() {
      return new InstanceTimeEntityTupleScheme();
    }
  }

  private static class InstanceTimeEntityTupleScheme extends org.apache.thrift.scheme.TupleScheme<InstanceTimeEntity> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, InstanceTimeEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetInstanceUpTime()) {
        optionals.set(0);
      }
      if (struct.isSetInstanceStartTime()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetInstanceUpTime()) {
        oprot.writeI64(struct.instanceUpTime);
      }
      if (struct.isSetInstanceStartTime()) {
        oprot.writeI64(struct.instanceStartTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, InstanceTimeEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.instanceUpTime = iprot.readI64();
        struct.setInstanceUpTimeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.instanceStartTime = iprot.readI64();
        struct.setInstanceStartTimeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

