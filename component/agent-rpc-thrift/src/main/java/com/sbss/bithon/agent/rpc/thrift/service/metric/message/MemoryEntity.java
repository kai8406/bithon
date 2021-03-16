/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2021-03-16")
public class MemoryEntity implements org.apache.thrift.TBase<MemoryEntity, MemoryEntity._Fields>, java.io.Serializable, Cloneable, Comparable<MemoryEntity> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MemoryEntity");

  private static final org.apache.thrift.protocol.TField MEM_FIELD_DESC = new org.apache.thrift.protocol.TField("mem", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField FREE_FIELD_DESC = new org.apache.thrift.protocol.TField("free", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new MemoryEntityStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new MemoryEntityTupleSchemeFactory();

  public long mem; // required
  public long free; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MEM((short)1, "mem"),
    FREE((short)2, "free");

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
        case 1: // MEM
          return MEM;
        case 2: // FREE
          return FREE;
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
  private static final int __MEM_ISSET_ID = 0;
  private static final int __FREE_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MEM, new org.apache.thrift.meta_data.FieldMetaData("mem", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.FREE, new org.apache.thrift.meta_data.FieldMetaData("free", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MemoryEntity.class, metaDataMap);
  }

  public MemoryEntity() {
  }

  public MemoryEntity(
    long mem,
    long free)
  {
    this();
    this.mem = mem;
    setMemIsSet(true);
    this.free = free;
    setFreeIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MemoryEntity(MemoryEntity other) {
    __isset_bitfield = other.__isset_bitfield;
    this.mem = other.mem;
    this.free = other.free;
  }

  public MemoryEntity deepCopy() {
    return new MemoryEntity(this);
  }

  @Override
  public void clear() {
    setMemIsSet(false);
    this.mem = 0;
    setFreeIsSet(false);
    this.free = 0;
  }

  public long getMem() {
    return this.mem;
  }

  public MemoryEntity setMem(long mem) {
    this.mem = mem;
    setMemIsSet(true);
    return this;
  }

  public void unsetMem() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __MEM_ISSET_ID);
  }

  /** Returns true if field mem is set (has been assigned a value) and false otherwise */
  public boolean isSetMem() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __MEM_ISSET_ID);
  }

  public void setMemIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __MEM_ISSET_ID, value);
  }

  public long getFree() {
    return this.free;
  }

  public MemoryEntity setFree(long free) {
    this.free = free;
    setFreeIsSet(true);
    return this;
  }

  public void unsetFree() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __FREE_ISSET_ID);
  }

  /** Returns true if field free is set (has been assigned a value) and false otherwise */
  public boolean isSetFree() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __FREE_ISSET_ID);
  }

  public void setFreeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __FREE_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case MEM:
      if (value == null) {
        unsetMem();
      } else {
        setMem((java.lang.Long)value);
      }
      break;

    case FREE:
      if (value == null) {
        unsetFree();
      } else {
        setFree((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case MEM:
      return getMem();

    case FREE:
      return getFree();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case MEM:
      return isSetMem();
    case FREE:
      return isSetFree();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof MemoryEntity)
      return this.equals((MemoryEntity)that);
    return false;
  }

  public boolean equals(MemoryEntity that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_mem = true;
    boolean that_present_mem = true;
    if (this_present_mem || that_present_mem) {
      if (!(this_present_mem && that_present_mem))
        return false;
      if (this.mem != that.mem)
        return false;
    }

    boolean this_present_free = true;
    boolean that_present_free = true;
    if (this_present_free || that_present_free) {
      if (!(this_present_free && that_present_free))
        return false;
      if (this.free != that.free)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(mem);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(free);

    return hashCode;
  }

  @Override
  public int compareTo(MemoryEntity other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetMem(), other.isSetMem());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMem()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.mem, other.mem);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetFree(), other.isSetFree());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFree()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.free, other.free);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("MemoryEntity(");
    boolean first = true;

    sb.append("mem:");
    sb.append(this.mem);
    first = false;
    if (!first) sb.append(", ");
    sb.append("free:");
    sb.append(this.free);
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

  private static class MemoryEntityStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MemoryEntityStandardScheme getScheme() {
      return new MemoryEntityStandardScheme();
    }
  }

  private static class MemoryEntityStandardScheme extends org.apache.thrift.scheme.StandardScheme<MemoryEntity> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MemoryEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MEM
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.mem = iprot.readI64();
              struct.setMemIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // FREE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.free = iprot.readI64();
              struct.setFreeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, MemoryEntity struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(MEM_FIELD_DESC);
      oprot.writeI64(struct.mem);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(FREE_FIELD_DESC);
      oprot.writeI64(struct.free);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MemoryEntityTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public MemoryEntityTupleScheme getScheme() {
      return new MemoryEntityTupleScheme();
    }
  }

  private static class MemoryEntityTupleScheme extends org.apache.thrift.scheme.TupleScheme<MemoryEntity> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MemoryEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMem()) {
        optionals.set(0);
      }
      if (struct.isSetFree()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetMem()) {
        oprot.writeI64(struct.mem);
      }
      if (struct.isSetFree()) {
        oprot.writeI64(struct.free);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MemoryEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.mem = iprot.readI64();
        struct.setMemIsSet(true);
      }
      if (incoming.get(1)) {
        struct.free = iprot.readI64();
        struct.setFreeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

