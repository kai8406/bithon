/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2021-03-16")
public class MetaspaceEntity implements org.apache.thrift.TBase<MetaspaceEntity, MetaspaceEntity._Fields>, java.io.Serializable, Cloneable, Comparable<MetaspaceEntity> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MetaspaceEntity");

  private static final org.apache.thrift.protocol.TField METASPACE_COMMITTED_FIELD_DESC = new org.apache.thrift.protocol.TField("metaspaceCommitted", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField METASPACE_USED_FIELD_DESC = new org.apache.thrift.protocol.TField("metaspaceUsed", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new MetaspaceEntityStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new MetaspaceEntityTupleSchemeFactory();

  public long metaspaceCommitted; // required
  public long metaspaceUsed; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    METASPACE_COMMITTED((short)1, "metaspaceCommitted"),
    METASPACE_USED((short)2, "metaspaceUsed");

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
        case 1: // METASPACE_COMMITTED
          return METASPACE_COMMITTED;
        case 2: // METASPACE_USED
          return METASPACE_USED;
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
  private static final int __METASPACECOMMITTED_ISSET_ID = 0;
  private static final int __METASPACEUSED_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.METASPACE_COMMITTED, new org.apache.thrift.meta_data.FieldMetaData("metaspaceCommitted", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.METASPACE_USED, new org.apache.thrift.meta_data.FieldMetaData("metaspaceUsed", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MetaspaceEntity.class, metaDataMap);
  }

  public MetaspaceEntity() {
  }

  public MetaspaceEntity(
    long metaspaceCommitted,
    long metaspaceUsed)
  {
    this();
    this.metaspaceCommitted = metaspaceCommitted;
    setMetaspaceCommittedIsSet(true);
    this.metaspaceUsed = metaspaceUsed;
    setMetaspaceUsedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MetaspaceEntity(MetaspaceEntity other) {
    __isset_bitfield = other.__isset_bitfield;
    this.metaspaceCommitted = other.metaspaceCommitted;
    this.metaspaceUsed = other.metaspaceUsed;
  }

  public MetaspaceEntity deepCopy() {
    return new MetaspaceEntity(this);
  }

  @Override
  public void clear() {
    setMetaspaceCommittedIsSet(false);
    this.metaspaceCommitted = 0;
    setMetaspaceUsedIsSet(false);
    this.metaspaceUsed = 0;
  }

  public long getMetaspaceCommitted() {
    return this.metaspaceCommitted;
  }

  public MetaspaceEntity setMetaspaceCommitted(long metaspaceCommitted) {
    this.metaspaceCommitted = metaspaceCommitted;
    setMetaspaceCommittedIsSet(true);
    return this;
  }

  public void unsetMetaspaceCommitted() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __METASPACECOMMITTED_ISSET_ID);
  }

  /** Returns true if field metaspaceCommitted is set (has been assigned a value) and false otherwise */
  public boolean isSetMetaspaceCommitted() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __METASPACECOMMITTED_ISSET_ID);
  }

  public void setMetaspaceCommittedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __METASPACECOMMITTED_ISSET_ID, value);
  }

  public long getMetaspaceUsed() {
    return this.metaspaceUsed;
  }

  public MetaspaceEntity setMetaspaceUsed(long metaspaceUsed) {
    this.metaspaceUsed = metaspaceUsed;
    setMetaspaceUsedIsSet(true);
    return this;
  }

  public void unsetMetaspaceUsed() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __METASPACEUSED_ISSET_ID);
  }

  /** Returns true if field metaspaceUsed is set (has been assigned a value) and false otherwise */
  public boolean isSetMetaspaceUsed() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __METASPACEUSED_ISSET_ID);
  }

  public void setMetaspaceUsedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __METASPACEUSED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case METASPACE_COMMITTED:
      if (value == null) {
        unsetMetaspaceCommitted();
      } else {
        setMetaspaceCommitted((java.lang.Long)value);
      }
      break;

    case METASPACE_USED:
      if (value == null) {
        unsetMetaspaceUsed();
      } else {
        setMetaspaceUsed((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case METASPACE_COMMITTED:
      return getMetaspaceCommitted();

    case METASPACE_USED:
      return getMetaspaceUsed();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case METASPACE_COMMITTED:
      return isSetMetaspaceCommitted();
    case METASPACE_USED:
      return isSetMetaspaceUsed();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof MetaspaceEntity)
      return this.equals((MetaspaceEntity)that);
    return false;
  }

  public boolean equals(MetaspaceEntity that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_metaspaceCommitted = true;
    boolean that_present_metaspaceCommitted = true;
    if (this_present_metaspaceCommitted || that_present_metaspaceCommitted) {
      if (!(this_present_metaspaceCommitted && that_present_metaspaceCommitted))
        return false;
      if (this.metaspaceCommitted != that.metaspaceCommitted)
        return false;
    }

    boolean this_present_metaspaceUsed = true;
    boolean that_present_metaspaceUsed = true;
    if (this_present_metaspaceUsed || that_present_metaspaceUsed) {
      if (!(this_present_metaspaceUsed && that_present_metaspaceUsed))
        return false;
      if (this.metaspaceUsed != that.metaspaceUsed)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(metaspaceCommitted);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(metaspaceUsed);

    return hashCode;
  }

  @Override
  public int compareTo(MetaspaceEntity other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetMetaspaceCommitted(), other.isSetMetaspaceCommitted());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMetaspaceCommitted()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.metaspaceCommitted, other.metaspaceCommitted);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetMetaspaceUsed(), other.isSetMetaspaceUsed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMetaspaceUsed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.metaspaceUsed, other.metaspaceUsed);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("MetaspaceEntity(");
    boolean first = true;

    sb.append("metaspaceCommitted:");
    sb.append(this.metaspaceCommitted);
    first = false;
    if (!first) sb.append(", ");
    sb.append("metaspaceUsed:");
    sb.append(this.metaspaceUsed);
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

  private static class MetaspaceEntityStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MetaspaceEntityStandardScheme getScheme() {
      return new MetaspaceEntityStandardScheme();
    }
  }

  private static class MetaspaceEntityStandardScheme extends org.apache.thrift.scheme.StandardScheme<MetaspaceEntity> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MetaspaceEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // METASPACE_COMMITTED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.metaspaceCommitted = iprot.readI64();
              struct.setMetaspaceCommittedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // METASPACE_USED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.metaspaceUsed = iprot.readI64();
              struct.setMetaspaceUsedIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, MetaspaceEntity struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(METASPACE_COMMITTED_FIELD_DESC);
      oprot.writeI64(struct.metaspaceCommitted);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(METASPACE_USED_FIELD_DESC);
      oprot.writeI64(struct.metaspaceUsed);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MetaspaceEntityTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MetaspaceEntityTupleScheme getScheme() {
      return new MetaspaceEntityTupleScheme();
    }
  }

  private static class MetaspaceEntityTupleScheme extends org.apache.thrift.scheme.TupleScheme<MetaspaceEntity> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MetaspaceEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMetaspaceCommitted()) {
        optionals.set(0);
      }
      if (struct.isSetMetaspaceUsed()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetMetaspaceCommitted()) {
        oprot.writeI64(struct.metaspaceCommitted);
      }
      if (struct.isSetMetaspaceUsed()) {
        oprot.writeI64(struct.metaspaceUsed);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MetaspaceEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.metaspaceCommitted = iprot.readI64();
        struct.setMetaspaceCommittedIsSet(true);
      }
      if (incoming.get(1)) {
        struct.metaspaceUsed = iprot.readI64();
        struct.setMetaspaceUsedIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

