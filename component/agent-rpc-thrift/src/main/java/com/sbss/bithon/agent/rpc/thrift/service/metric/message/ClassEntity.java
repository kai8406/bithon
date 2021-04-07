/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2021-03-28")
public class ClassEntity implements org.apache.thrift.TBase<ClassEntity, ClassEntity._Fields>, java.io.Serializable, Cloneable, Comparable<ClassEntity> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ClassEntity");

  private static final org.apache.thrift.protocol.TField CURRENT_LOADED_FIELD_DESC = new org.apache.thrift.protocol.TField("currentLoaded", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField TOTAL_LOADED_FIELD_DESC = new org.apache.thrift.protocol.TField("totalLoaded", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_UNLOADED_FIELD_DESC = new org.apache.thrift.protocol.TField("totalUnloaded", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ClassEntityStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ClassEntityTupleSchemeFactory();

  public long currentLoaded; // required
  public long totalLoaded; // required
  public long totalUnloaded; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CURRENT_LOADED((short)1, "currentLoaded"),
    TOTAL_LOADED((short)2, "totalLoaded"),
    TOTAL_UNLOADED((short)3, "totalUnloaded");

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
        case 1: // CURRENT_LOADED
          return CURRENT_LOADED;
        case 2: // TOTAL_LOADED
          return TOTAL_LOADED;
        case 3: // TOTAL_UNLOADED
          return TOTAL_UNLOADED;
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
  private static final int __CURRENTLOADED_ISSET_ID = 0;
  private static final int __TOTALLOADED_ISSET_ID = 1;
  private static final int __TOTALUNLOADED_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CURRENT_LOADED, new org.apache.thrift.meta_data.FieldMetaData("currentLoaded", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL_LOADED, new org.apache.thrift.meta_data.FieldMetaData("totalLoaded", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL_UNLOADED, new org.apache.thrift.meta_data.FieldMetaData("totalUnloaded", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ClassEntity.class, metaDataMap);
  }

  public ClassEntity() {
  }

  public ClassEntity(
    long currentLoaded,
    long totalLoaded,
    long totalUnloaded)
  {
    this();
    this.currentLoaded = currentLoaded;
    setCurrentLoadedIsSet(true);
    this.totalLoaded = totalLoaded;
    setTotalLoadedIsSet(true);
    this.totalUnloaded = totalUnloaded;
    setTotalUnloadedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ClassEntity(ClassEntity other) {
    __isset_bitfield = other.__isset_bitfield;
    this.currentLoaded = other.currentLoaded;
    this.totalLoaded = other.totalLoaded;
    this.totalUnloaded = other.totalUnloaded;
  }

  public ClassEntity deepCopy() {
    return new ClassEntity(this);
  }

  @Override
  public void clear() {
    setCurrentLoadedIsSet(false);
    this.currentLoaded = 0;
    setTotalLoadedIsSet(false);
    this.totalLoaded = 0;
    setTotalUnloadedIsSet(false);
    this.totalUnloaded = 0;
  }

  public long getCurrentLoaded() {
    return this.currentLoaded;
  }

  public ClassEntity setCurrentLoaded(long currentLoaded) {
    this.currentLoaded = currentLoaded;
    setCurrentLoadedIsSet(true);
    return this;
  }

  public void unsetCurrentLoaded() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CURRENTLOADED_ISSET_ID);
  }

  /** Returns true if field currentLoaded is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentLoaded() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CURRENTLOADED_ISSET_ID);
  }

  public void setCurrentLoadedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CURRENTLOADED_ISSET_ID, value);
  }

  public long getTotalLoaded() {
    return this.totalLoaded;
  }

  public ClassEntity setTotalLoaded(long totalLoaded) {
    this.totalLoaded = totalLoaded;
    setTotalLoadedIsSet(true);
    return this;
  }

  public void unsetTotalLoaded() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOTALLOADED_ISSET_ID);
  }

  /** Returns true if field totalLoaded is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalLoaded() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOTALLOADED_ISSET_ID);
  }

  public void setTotalLoadedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOTALLOADED_ISSET_ID, value);
  }

  public long getTotalUnloaded() {
    return this.totalUnloaded;
  }

  public ClassEntity setTotalUnloaded(long totalUnloaded) {
    this.totalUnloaded = totalUnloaded;
    setTotalUnloadedIsSet(true);
    return this;
  }

  public void unsetTotalUnloaded() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOTALUNLOADED_ISSET_ID);
  }

  /** Returns true if field totalUnloaded is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalUnloaded() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOTALUNLOADED_ISSET_ID);
  }

  public void setTotalUnloadedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOTALUNLOADED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case CURRENT_LOADED:
      if (value == null) {
        unsetCurrentLoaded();
      } else {
        setCurrentLoaded((java.lang.Long)value);
      }
      break;

    case TOTAL_LOADED:
      if (value == null) {
        unsetTotalLoaded();
      } else {
        setTotalLoaded((java.lang.Long)value);
      }
      break;

    case TOTAL_UNLOADED:
      if (value == null) {
        unsetTotalUnloaded();
      } else {
        setTotalUnloaded((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CURRENT_LOADED:
      return getCurrentLoaded();

    case TOTAL_LOADED:
      return getTotalLoaded();

    case TOTAL_UNLOADED:
      return getTotalUnloaded();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CURRENT_LOADED:
      return isSetCurrentLoaded();
    case TOTAL_LOADED:
      return isSetTotalLoaded();
    case TOTAL_UNLOADED:
      return isSetTotalUnloaded();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof ClassEntity)
      return this.equals((ClassEntity)that);
    return false;
  }

  public boolean equals(ClassEntity that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_currentLoaded = true;
    boolean that_present_currentLoaded = true;
    if (this_present_currentLoaded || that_present_currentLoaded) {
      if (!(this_present_currentLoaded && that_present_currentLoaded))
        return false;
      if (this.currentLoaded != that.currentLoaded)
        return false;
    }

    boolean this_present_totalLoaded = true;
    boolean that_present_totalLoaded = true;
    if (this_present_totalLoaded || that_present_totalLoaded) {
      if (!(this_present_totalLoaded && that_present_totalLoaded))
        return false;
      if (this.totalLoaded != that.totalLoaded)
        return false;
    }

    boolean this_present_totalUnloaded = true;
    boolean that_present_totalUnloaded = true;
    if (this_present_totalUnloaded || that_present_totalUnloaded) {
      if (!(this_present_totalUnloaded && that_present_totalUnloaded))
        return false;
      if (this.totalUnloaded != that.totalUnloaded)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(currentLoaded);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(totalLoaded);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(totalUnloaded);

    return hashCode;
  }

  @Override
  public int compareTo(ClassEntity other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetCurrentLoaded(), other.isSetCurrentLoaded());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentLoaded()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentLoaded, other.currentLoaded);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetTotalLoaded(), other.isSetTotalLoaded());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalLoaded()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalLoaded, other.totalLoaded);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetTotalUnloaded(), other.isSetTotalUnloaded());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalUnloaded()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalUnloaded, other.totalUnloaded);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ClassEntity(");
    boolean first = true;

    sb.append("currentLoaded:");
    sb.append(this.currentLoaded);
    first = false;
    if (!first) sb.append(", ");
    sb.append("totalLoaded:");
    sb.append(this.totalLoaded);
    first = false;
    if (!first) sb.append(", ");
    sb.append("totalUnloaded:");
    sb.append(this.totalUnloaded);
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

  private static class ClassEntityStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ClassEntityStandardScheme getScheme() {
      return new ClassEntityStandardScheme();
    }
  }

  private static class ClassEntityStandardScheme extends org.apache.thrift.scheme.StandardScheme<ClassEntity> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ClassEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CURRENT_LOADED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.currentLoaded = iprot.readI64();
              struct.setCurrentLoadedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TOTAL_LOADED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.totalLoaded = iprot.readI64();
              struct.setTotalLoadedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL_UNLOADED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.totalUnloaded = iprot.readI64();
              struct.setTotalUnloadedIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ClassEntity struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(CURRENT_LOADED_FIELD_DESC);
      oprot.writeI64(struct.currentLoaded);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_LOADED_FIELD_DESC);
      oprot.writeI64(struct.totalLoaded);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_UNLOADED_FIELD_DESC);
      oprot.writeI64(struct.totalUnloaded);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ClassEntityTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ClassEntityTupleScheme getScheme() {
      return new ClassEntityTupleScheme();
    }
  }

  private static class ClassEntityTupleScheme extends org.apache.thrift.scheme.TupleScheme<ClassEntity> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ClassEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCurrentLoaded()) {
        optionals.set(0);
      }
      if (struct.isSetTotalLoaded()) {
        optionals.set(1);
      }
      if (struct.isSetTotalUnloaded()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetCurrentLoaded()) {
        oprot.writeI64(struct.currentLoaded);
      }
      if (struct.isSetTotalLoaded()) {
        oprot.writeI64(struct.totalLoaded);
      }
      if (struct.isSetTotalUnloaded()) {
        oprot.writeI64(struct.totalUnloaded);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ClassEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.currentLoaded = iprot.readI64();
        struct.setCurrentLoadedIsSet(true);
      }
      if (incoming.get(1)) {
        struct.totalLoaded = iprot.readI64();
        struct.setTotalLoadedIsSet(true);
      }
      if (incoming.get(2)) {
        struct.totalUnloaded = iprot.readI64();
        struct.setTotalUnloadedIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

