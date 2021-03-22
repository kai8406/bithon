/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2021-03-22")
public class HeapEntity implements org.apache.thrift.TBase<HeapEntity, HeapEntity._Fields>, java.io.Serializable, Cloneable, Comparable<HeapEntity> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("HeapEntity");

  private static final org.apache.thrift.protocol.TField HEAP_FIELD_DESC = new org.apache.thrift.protocol.TField("heap", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField HEAP_INIT_FIELD_DESC = new org.apache.thrift.protocol.TField("heapInit", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField HEAP_USED_FIELD_DESC = new org.apache.thrift.protocol.TField("heapUsed", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField HEAP_COMMITTED_FIELD_DESC = new org.apache.thrift.protocol.TField("heapCommitted", org.apache.thrift.protocol.TType.I64, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new HeapEntityStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new HeapEntityTupleSchemeFactory();

  public long heap; // required
  public long heapInit; // required
  public long heapUsed; // required
  public long heapCommitted; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    HEAP((short)1, "heap"),
    HEAP_INIT((short)2, "heapInit"),
    HEAP_USED((short)3, "heapUsed"),
    HEAP_COMMITTED((short)4, "heapCommitted");

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
        case 1: // HEAP
          return HEAP;
        case 2: // HEAP_INIT
          return HEAP_INIT;
        case 3: // HEAP_USED
          return HEAP_USED;
        case 4: // HEAP_COMMITTED
          return HEAP_COMMITTED;
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
  private static final int __HEAP_ISSET_ID = 0;
  private static final int __HEAPINIT_ISSET_ID = 1;
  private static final int __HEAPUSED_ISSET_ID = 2;
  private static final int __HEAPCOMMITTED_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.HEAP, new org.apache.thrift.meta_data.FieldMetaData("heap", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.HEAP_INIT, new org.apache.thrift.meta_data.FieldMetaData("heapInit", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.HEAP_USED, new org.apache.thrift.meta_data.FieldMetaData("heapUsed", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.HEAP_COMMITTED, new org.apache.thrift.meta_data.FieldMetaData("heapCommitted", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(HeapEntity.class, metaDataMap);
  }

  public HeapEntity() {
  }

  public HeapEntity(
    long heap,
    long heapInit,
    long heapUsed,
    long heapCommitted)
  {
    this();
    this.heap = heap;
    setHeapIsSet(true);
    this.heapInit = heapInit;
    setHeapInitIsSet(true);
    this.heapUsed = heapUsed;
    setHeapUsedIsSet(true);
    this.heapCommitted = heapCommitted;
    setHeapCommittedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public HeapEntity(HeapEntity other) {
    __isset_bitfield = other.__isset_bitfield;
    this.heap = other.heap;
    this.heapInit = other.heapInit;
    this.heapUsed = other.heapUsed;
    this.heapCommitted = other.heapCommitted;
  }

  public HeapEntity deepCopy() {
    return new HeapEntity(this);
  }

  @Override
  public void clear() {
    setHeapIsSet(false);
    this.heap = 0;
    setHeapInitIsSet(false);
    this.heapInit = 0;
    setHeapUsedIsSet(false);
    this.heapUsed = 0;
    setHeapCommittedIsSet(false);
    this.heapCommitted = 0;
  }

  public long getHeap() {
    return this.heap;
  }

  public HeapEntity setHeap(long heap) {
    this.heap = heap;
    setHeapIsSet(true);
    return this;
  }

  public void unsetHeap() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __HEAP_ISSET_ID);
  }

  /** Returns true if field heap is set (has been assigned a value) and false otherwise */
  public boolean isSetHeap() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __HEAP_ISSET_ID);
  }

  public void setHeapIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __HEAP_ISSET_ID, value);
  }

  public long getHeapInit() {
    return this.heapInit;
  }

  public HeapEntity setHeapInit(long heapInit) {
    this.heapInit = heapInit;
    setHeapInitIsSet(true);
    return this;
  }

  public void unsetHeapInit() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __HEAPINIT_ISSET_ID);
  }

  /** Returns true if field heapInit is set (has been assigned a value) and false otherwise */
  public boolean isSetHeapInit() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __HEAPINIT_ISSET_ID);
  }

  public void setHeapInitIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __HEAPINIT_ISSET_ID, value);
  }

  public long getHeapUsed() {
    return this.heapUsed;
  }

  public HeapEntity setHeapUsed(long heapUsed) {
    this.heapUsed = heapUsed;
    setHeapUsedIsSet(true);
    return this;
  }

  public void unsetHeapUsed() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __HEAPUSED_ISSET_ID);
  }

  /** Returns true if field heapUsed is set (has been assigned a value) and false otherwise */
  public boolean isSetHeapUsed() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __HEAPUSED_ISSET_ID);
  }

  public void setHeapUsedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __HEAPUSED_ISSET_ID, value);
  }

  public long getHeapCommitted() {
    return this.heapCommitted;
  }

  public HeapEntity setHeapCommitted(long heapCommitted) {
    this.heapCommitted = heapCommitted;
    setHeapCommittedIsSet(true);
    return this;
  }

  public void unsetHeapCommitted() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __HEAPCOMMITTED_ISSET_ID);
  }

  /** Returns true if field heapCommitted is set (has been assigned a value) and false otherwise */
  public boolean isSetHeapCommitted() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __HEAPCOMMITTED_ISSET_ID);
  }

  public void setHeapCommittedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __HEAPCOMMITTED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case HEAP:
      if (value == null) {
        unsetHeap();
      } else {
        setHeap((java.lang.Long)value);
      }
      break;

    case HEAP_INIT:
      if (value == null) {
        unsetHeapInit();
      } else {
        setHeapInit((java.lang.Long)value);
      }
      break;

    case HEAP_USED:
      if (value == null) {
        unsetHeapUsed();
      } else {
        setHeapUsed((java.lang.Long)value);
      }
      break;

    case HEAP_COMMITTED:
      if (value == null) {
        unsetHeapCommitted();
      } else {
        setHeapCommitted((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case HEAP:
      return getHeap();

    case HEAP_INIT:
      return getHeapInit();

    case HEAP_USED:
      return getHeapUsed();

    case HEAP_COMMITTED:
      return getHeapCommitted();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case HEAP:
      return isSetHeap();
    case HEAP_INIT:
      return isSetHeapInit();
    case HEAP_USED:
      return isSetHeapUsed();
    case HEAP_COMMITTED:
      return isSetHeapCommitted();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof HeapEntity)
      return this.equals((HeapEntity)that);
    return false;
  }

  public boolean equals(HeapEntity that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_heap = true;
    boolean that_present_heap = true;
    if (this_present_heap || that_present_heap) {
      if (!(this_present_heap && that_present_heap))
        return false;
      if (this.heap != that.heap)
        return false;
    }

    boolean this_present_heapInit = true;
    boolean that_present_heapInit = true;
    if (this_present_heapInit || that_present_heapInit) {
      if (!(this_present_heapInit && that_present_heapInit))
        return false;
      if (this.heapInit != that.heapInit)
        return false;
    }

    boolean this_present_heapUsed = true;
    boolean that_present_heapUsed = true;
    if (this_present_heapUsed || that_present_heapUsed) {
      if (!(this_present_heapUsed && that_present_heapUsed))
        return false;
      if (this.heapUsed != that.heapUsed)
        return false;
    }

    boolean this_present_heapCommitted = true;
    boolean that_present_heapCommitted = true;
    if (this_present_heapCommitted || that_present_heapCommitted) {
      if (!(this_present_heapCommitted && that_present_heapCommitted))
        return false;
      if (this.heapCommitted != that.heapCommitted)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(heap);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(heapInit);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(heapUsed);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(heapCommitted);

    return hashCode;
  }

  @Override
  public int compareTo(HeapEntity other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetHeap(), other.isSetHeap());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeap()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.heap, other.heap);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetHeapInit(), other.isSetHeapInit());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeapInit()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.heapInit, other.heapInit);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetHeapUsed(), other.isSetHeapUsed());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeapUsed()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.heapUsed, other.heapUsed);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetHeapCommitted(), other.isSetHeapCommitted());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHeapCommitted()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.heapCommitted, other.heapCommitted);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("HeapEntity(");
    boolean first = true;

    sb.append("heap:");
    sb.append(this.heap);
    first = false;
    if (!first) sb.append(", ");
    sb.append("heapInit:");
    sb.append(this.heapInit);
    first = false;
    if (!first) sb.append(", ");
    sb.append("heapUsed:");
    sb.append(this.heapUsed);
    first = false;
    if (!first) sb.append(", ");
    sb.append("heapCommitted:");
    sb.append(this.heapCommitted);
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

  private static class HeapEntityStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HeapEntityStandardScheme getScheme() {
      return new HeapEntityStandardScheme();
    }
  }

  private static class HeapEntityStandardScheme extends org.apache.thrift.scheme.StandardScheme<HeapEntity> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, HeapEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // HEAP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.heap = iprot.readI64();
              struct.setHeapIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // HEAP_INIT
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.heapInit = iprot.readI64();
              struct.setHeapInitIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // HEAP_USED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.heapUsed = iprot.readI64();
              struct.setHeapUsedIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // HEAP_COMMITTED
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.heapCommitted = iprot.readI64();
              struct.setHeapCommittedIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, HeapEntity struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(HEAP_FIELD_DESC);
      oprot.writeI64(struct.heap);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(HEAP_INIT_FIELD_DESC);
      oprot.writeI64(struct.heapInit);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(HEAP_USED_FIELD_DESC);
      oprot.writeI64(struct.heapUsed);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(HEAP_COMMITTED_FIELD_DESC);
      oprot.writeI64(struct.heapCommitted);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class HeapEntityTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public HeapEntityTupleScheme getScheme() {
      return new HeapEntityTupleScheme();
    }
  }

  private static class HeapEntityTupleScheme extends org.apache.thrift.scheme.TupleScheme<HeapEntity> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, HeapEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetHeap()) {
        optionals.set(0);
      }
      if (struct.isSetHeapInit()) {
        optionals.set(1);
      }
      if (struct.isSetHeapUsed()) {
        optionals.set(2);
      }
      if (struct.isSetHeapCommitted()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetHeap()) {
        oprot.writeI64(struct.heap);
      }
      if (struct.isSetHeapInit()) {
        oprot.writeI64(struct.heapInit);
      }
      if (struct.isSetHeapUsed()) {
        oprot.writeI64(struct.heapUsed);
      }
      if (struct.isSetHeapCommitted()) {
        oprot.writeI64(struct.heapCommitted);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, HeapEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.heap = iprot.readI64();
        struct.setHeapIsSet(true);
      }
      if (incoming.get(1)) {
        struct.heapInit = iprot.readI64();
        struct.setHeapInitIsSet(true);
      }
      if (incoming.get(2)) {
        struct.heapUsed = iprot.readI64();
        struct.setHeapUsedIsSet(true);
      }
      if (incoming.get(3)) {
        struct.heapCommitted = iprot.readI64();
        struct.setHeapCommittedIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

