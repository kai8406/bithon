/**
 * Autogenerated by Thrift Compiler (0.13.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.13.0)", date = "2021-03-03")
public class ClassEntity implements org.apache.thrift.TBase<ClassEntity, ClassEntity._Fields>, java.io.Serializable, Cloneable, Comparable<ClassEntity> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ClassEntity");

  private static final org.apache.thrift.protocol.TField CLASSES_FIELD_DESC = new org.apache.thrift.protocol.TField("classes", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField LOADED_FIELD_DESC = new org.apache.thrift.protocol.TField("loaded", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField UNLOADED_FIELD_DESC = new org.apache.thrift.protocol.TField("unloaded", org.apache.thrift.protocol.TType.I64, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ClassEntityStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ClassEntityTupleSchemeFactory();

  public long classes; // required
  public long loaded; // required
  public long unloaded; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CLASSES((short)1, "classes"),
    LOADED((short)2, "loaded"),
    UNLOADED((short)3, "unloaded");

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
        case 1: // CLASSES
          return CLASSES;
        case 2: // LOADED
          return LOADED;
        case 3: // UNLOADED
          return UNLOADED;
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
  private static final int __CLASSES_ISSET_ID = 0;
  private static final int __LOADED_ISSET_ID = 1;
  private static final int __UNLOADED_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CLASSES, new org.apache.thrift.meta_data.FieldMetaData("classes", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.LOADED, new org.apache.thrift.meta_data.FieldMetaData("loaded", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.UNLOADED, new org.apache.thrift.meta_data.FieldMetaData("unloaded", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ClassEntity.class, metaDataMap);
  }

  public ClassEntity() {
  }

  public ClassEntity(
    long classes,
    long loaded,
    long unloaded)
  {
    this();
    this.classes = classes;
    setClassesIsSet(true);
    this.loaded = loaded;
    setLoadedIsSet(true);
    this.unloaded = unloaded;
    setUnloadedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ClassEntity(ClassEntity other) {
    __isset_bitfield = other.__isset_bitfield;
    this.classes = other.classes;
    this.loaded = other.loaded;
    this.unloaded = other.unloaded;
  }

  public ClassEntity deepCopy() {
    return new ClassEntity(this);
  }

  @Override
  public void clear() {
    setClassesIsSet(false);
    this.classes = 0;
    setLoadedIsSet(false);
    this.loaded = 0;
    setUnloadedIsSet(false);
    this.unloaded = 0;
  }

  public long getClasses() {
    return this.classes;
  }

  public ClassEntity setClasses(long classes) {
    this.classes = classes;
    setClassesIsSet(true);
    return this;
  }

  public void unsetClasses() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CLASSES_ISSET_ID);
  }

  /** Returns true if field classes is set (has been assigned a value) and false otherwise */
  public boolean isSetClasses() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CLASSES_ISSET_ID);
  }

  public void setClassesIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CLASSES_ISSET_ID, value);
  }

  public long getLoaded() {
    return this.loaded;
  }

  public ClassEntity setLoaded(long loaded) {
    this.loaded = loaded;
    setLoadedIsSet(true);
    return this;
  }

  public void unsetLoaded() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __LOADED_ISSET_ID);
  }

  /** Returns true if field loaded is set (has been assigned a value) and false otherwise */
  public boolean isSetLoaded() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __LOADED_ISSET_ID);
  }

  public void setLoadedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __LOADED_ISSET_ID, value);
  }

  public long getUnloaded() {
    return this.unloaded;
  }

  public ClassEntity setUnloaded(long unloaded) {
    this.unloaded = unloaded;
    setUnloadedIsSet(true);
    return this;
  }

  public void unsetUnloaded() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __UNLOADED_ISSET_ID);
  }

  /** Returns true if field unloaded is set (has been assigned a value) and false otherwise */
  public boolean isSetUnloaded() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __UNLOADED_ISSET_ID);
  }

  public void setUnloadedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __UNLOADED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case CLASSES:
      if (value == null) {
        unsetClasses();
      } else {
        setClasses((java.lang.Long)value);
      }
      break;

    case LOADED:
      if (value == null) {
        unsetLoaded();
      } else {
        setLoaded((java.lang.Long)value);
      }
      break;

    case UNLOADED:
      if (value == null) {
        unsetUnloaded();
      } else {
        setUnloaded((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CLASSES:
      return getClasses();

    case LOADED:
      return getLoaded();

    case UNLOADED:
      return getUnloaded();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CLASSES:
      return isSetClasses();
    case LOADED:
      return isSetLoaded();
    case UNLOADED:
      return isSetUnloaded();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof ClassEntity)
      return this.equals((ClassEntity)that);
    return false;
  }

  public boolean equals(ClassEntity that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_classes = true;
    boolean that_present_classes = true;
    if (this_present_classes || that_present_classes) {
      if (!(this_present_classes && that_present_classes))
        return false;
      if (this.classes != that.classes)
        return false;
    }

    boolean this_present_loaded = true;
    boolean that_present_loaded = true;
    if (this_present_loaded || that_present_loaded) {
      if (!(this_present_loaded && that_present_loaded))
        return false;
      if (this.loaded != that.loaded)
        return false;
    }

    boolean this_present_unloaded = true;
    boolean that_present_unloaded = true;
    if (this_present_unloaded || that_present_unloaded) {
      if (!(this_present_unloaded && that_present_unloaded))
        return false;
      if (this.unloaded != that.unloaded)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(classes);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(loaded);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(unloaded);

    return hashCode;
  }

  @Override
  public int compareTo(ClassEntity other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetClasses()).compareTo(other.isSetClasses());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClasses()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.classes, other.classes);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetLoaded()).compareTo(other.isSetLoaded());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLoaded()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.loaded, other.loaded);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUnloaded()).compareTo(other.isSetUnloaded());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUnloaded()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.unloaded, other.unloaded);
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

    sb.append("classes:");
    sb.append(this.classes);
    first = false;
    if (!first) sb.append(", ");
    sb.append("loaded:");
    sb.append(this.loaded);
    first = false;
    if (!first) sb.append(", ");
    sb.append("unloaded:");
    sb.append(this.unloaded);
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
          case 1: // CLASSES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.classes = iprot.readI64();
              struct.setClassesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // LOADED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.loaded = iprot.readI64();
              struct.setLoadedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // UNLOADED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.unloaded = iprot.readI64();
              struct.setUnloadedIsSet(true);
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
      oprot.writeFieldBegin(CLASSES_FIELD_DESC);
      oprot.writeI64(struct.classes);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(LOADED_FIELD_DESC);
      oprot.writeI64(struct.loaded);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(UNLOADED_FIELD_DESC);
      oprot.writeI64(struct.unloaded);
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
      if (struct.isSetClasses()) {
        optionals.set(0);
      }
      if (struct.isSetLoaded()) {
        optionals.set(1);
      }
      if (struct.isSetUnloaded()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetClasses()) {
        oprot.writeI64(struct.classes);
      }
      if (struct.isSetLoaded()) {
        oprot.writeI64(struct.loaded);
      }
      if (struct.isSetUnloaded()) {
        oprot.writeI64(struct.unloaded);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ClassEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.classes = iprot.readI64();
        struct.setClassesIsSet(true);
      }
      if (incoming.get(1)) {
        struct.loaded = iprot.readI64();
        struct.setLoadedIsSet(true);
      }
      if (incoming.get(2)) {
        struct.unloaded = iprot.readI64();
        struct.setUnloadedIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

