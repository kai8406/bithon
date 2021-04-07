/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.metric.message;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2021-03-28")
public class ThreadEntity implements org.apache.thrift.TBase<ThreadEntity, ThreadEntity._Fields>, java.io.Serializable, Cloneable, Comparable<ThreadEntity> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("ThreadEntity");

  private static final org.apache.thrift.protocol.TField PEAK_THREADS_FIELD_DESC = new org.apache.thrift.protocol.TField("peakThreads", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField DAEMON_THREADS_FIELD_DESC = new org.apache.thrift.protocol.TField("daemonThreads", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_THREADS_FIELD_DESC = new org.apache.thrift.protocol.TField("totalThreads", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField ACTIVE_THREADS_FIELD_DESC = new org.apache.thrift.protocol.TField("activeThreads", org.apache.thrift.protocol.TType.I64, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new ThreadEntityStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new ThreadEntityTupleSchemeFactory();

  public long peakThreads; // required
  public long daemonThreads; // required
  public long totalThreads; // required
  public long activeThreads; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    PEAK_THREADS((short)1, "peakThreads"),
    DAEMON_THREADS((short)2, "daemonThreads"),
    TOTAL_THREADS((short)3, "totalThreads"),
    ACTIVE_THREADS((short)4, "activeThreads");

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
        case 1: // PEAK_THREADS
          return PEAK_THREADS;
        case 2: // DAEMON_THREADS
          return DAEMON_THREADS;
        case 3: // TOTAL_THREADS
          return TOTAL_THREADS;
        case 4: // ACTIVE_THREADS
          return ACTIVE_THREADS;
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
  private static final int __PEAKTHREADS_ISSET_ID = 0;
  private static final int __DAEMONTHREADS_ISSET_ID = 1;
  private static final int __TOTALTHREADS_ISSET_ID = 2;
  private static final int __ACTIVETHREADS_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.PEAK_THREADS, new org.apache.thrift.meta_data.FieldMetaData("peakThreads", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.DAEMON_THREADS, new org.apache.thrift.meta_data.FieldMetaData("daemonThreads", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL_THREADS, new org.apache.thrift.meta_data.FieldMetaData("totalThreads", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ACTIVE_THREADS, new org.apache.thrift.meta_data.FieldMetaData("activeThreads", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(ThreadEntity.class, metaDataMap);
  }

  public ThreadEntity() {
  }

  public ThreadEntity(
    long peakThreads,
    long daemonThreads,
    long totalThreads,
    long activeThreads)
  {
    this();
    this.peakThreads = peakThreads;
    setPeakThreadsIsSet(true);
    this.daemonThreads = daemonThreads;
    setDaemonThreadsIsSet(true);
    this.totalThreads = totalThreads;
    setTotalThreadsIsSet(true);
    this.activeThreads = activeThreads;
    setActiveThreadsIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public ThreadEntity(ThreadEntity other) {
    __isset_bitfield = other.__isset_bitfield;
    this.peakThreads = other.peakThreads;
    this.daemonThreads = other.daemonThreads;
    this.totalThreads = other.totalThreads;
    this.activeThreads = other.activeThreads;
  }

  public ThreadEntity deepCopy() {
    return new ThreadEntity(this);
  }

  @Override
  public void clear() {
    setPeakThreadsIsSet(false);
    this.peakThreads = 0;
    setDaemonThreadsIsSet(false);
    this.daemonThreads = 0;
    setTotalThreadsIsSet(false);
    this.totalThreads = 0;
    setActiveThreadsIsSet(false);
    this.activeThreads = 0;
  }

  public long getPeakThreads() {
    return this.peakThreads;
  }

  public ThreadEntity setPeakThreads(long peakThreads) {
    this.peakThreads = peakThreads;
    setPeakThreadsIsSet(true);
    return this;
  }

  public void unsetPeakThreads() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PEAKTHREADS_ISSET_ID);
  }

  /** Returns true if field peakThreads is set (has been assigned a value) and false otherwise */
  public boolean isSetPeakThreads() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PEAKTHREADS_ISSET_ID);
  }

  public void setPeakThreadsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PEAKTHREADS_ISSET_ID, value);
  }

  public long getDaemonThreads() {
    return this.daemonThreads;
  }

  public ThreadEntity setDaemonThreads(long daemonThreads) {
    this.daemonThreads = daemonThreads;
    setDaemonThreadsIsSet(true);
    return this;
  }

  public void unsetDaemonThreads() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __DAEMONTHREADS_ISSET_ID);
  }

  /** Returns true if field daemonThreads is set (has been assigned a value) and false otherwise */
  public boolean isSetDaemonThreads() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __DAEMONTHREADS_ISSET_ID);
  }

  public void setDaemonThreadsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __DAEMONTHREADS_ISSET_ID, value);
  }

  public long getTotalThreads() {
    return this.totalThreads;
  }

  public ThreadEntity setTotalThreads(long totalThreads) {
    this.totalThreads = totalThreads;
    setTotalThreadsIsSet(true);
    return this;
  }

  public void unsetTotalThreads() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOTALTHREADS_ISSET_ID);
  }

  /** Returns true if field totalThreads is set (has been assigned a value) and false otherwise */
  public boolean isSetTotalThreads() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOTALTHREADS_ISSET_ID);
  }

  public void setTotalThreadsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOTALTHREADS_ISSET_ID, value);
  }

  public long getActiveThreads() {
    return this.activeThreads;
  }

  public ThreadEntity setActiveThreads(long activeThreads) {
    this.activeThreads = activeThreads;
    setActiveThreadsIsSet(true);
    return this;
  }

  public void unsetActiveThreads() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ACTIVETHREADS_ISSET_ID);
  }

  /** Returns true if field activeThreads is set (has been assigned a value) and false otherwise */
  public boolean isSetActiveThreads() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ACTIVETHREADS_ISSET_ID);
  }

  public void setActiveThreadsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ACTIVETHREADS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case PEAK_THREADS:
      if (value == null) {
        unsetPeakThreads();
      } else {
        setPeakThreads((java.lang.Long)value);
      }
      break;

    case DAEMON_THREADS:
      if (value == null) {
        unsetDaemonThreads();
      } else {
        setDaemonThreads((java.lang.Long)value);
      }
      break;

    case TOTAL_THREADS:
      if (value == null) {
        unsetTotalThreads();
      } else {
        setTotalThreads((java.lang.Long)value);
      }
      break;

    case ACTIVE_THREADS:
      if (value == null) {
        unsetActiveThreads();
      } else {
        setActiveThreads((java.lang.Long)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case PEAK_THREADS:
      return getPeakThreads();

    case DAEMON_THREADS:
      return getDaemonThreads();

    case TOTAL_THREADS:
      return getTotalThreads();

    case ACTIVE_THREADS:
      return getActiveThreads();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case PEAK_THREADS:
      return isSetPeakThreads();
    case DAEMON_THREADS:
      return isSetDaemonThreads();
    case TOTAL_THREADS:
      return isSetTotalThreads();
    case ACTIVE_THREADS:
      return isSetActiveThreads();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that instanceof ThreadEntity)
      return this.equals((ThreadEntity)that);
    return false;
  }

  public boolean equals(ThreadEntity that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_peakThreads = true;
    boolean that_present_peakThreads = true;
    if (this_present_peakThreads || that_present_peakThreads) {
      if (!(this_present_peakThreads && that_present_peakThreads))
        return false;
      if (this.peakThreads != that.peakThreads)
        return false;
    }

    boolean this_present_daemonThreads = true;
    boolean that_present_daemonThreads = true;
    if (this_present_daemonThreads || that_present_daemonThreads) {
      if (!(this_present_daemonThreads && that_present_daemonThreads))
        return false;
      if (this.daemonThreads != that.daemonThreads)
        return false;
    }

    boolean this_present_totalThreads = true;
    boolean that_present_totalThreads = true;
    if (this_present_totalThreads || that_present_totalThreads) {
      if (!(this_present_totalThreads && that_present_totalThreads))
        return false;
      if (this.totalThreads != that.totalThreads)
        return false;
    }

    boolean this_present_activeThreads = true;
    boolean that_present_activeThreads = true;
    if (this_present_activeThreads || that_present_activeThreads) {
      if (!(this_present_activeThreads && that_present_activeThreads))
        return false;
      if (this.activeThreads != that.activeThreads)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(peakThreads);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(daemonThreads);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(totalThreads);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(activeThreads);

    return hashCode;
  }

  @Override
  public int compareTo(ThreadEntity other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.compare(isSetPeakThreads(), other.isSetPeakThreads());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPeakThreads()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.peakThreads, other.peakThreads);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetDaemonThreads(), other.isSetDaemonThreads());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDaemonThreads()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.daemonThreads, other.daemonThreads);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetTotalThreads(), other.isSetTotalThreads());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotalThreads()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.totalThreads, other.totalThreads);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.compare(isSetActiveThreads(), other.isSetActiveThreads());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetActiveThreads()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.activeThreads, other.activeThreads);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("ThreadEntity(");
    boolean first = true;

    sb.append("peakThreads:");
    sb.append(this.peakThreads);
    first = false;
    if (!first) sb.append(", ");
    sb.append("daemonThreads:");
    sb.append(this.daemonThreads);
    first = false;
    if (!first) sb.append(", ");
    sb.append("totalThreads:");
    sb.append(this.totalThreads);
    first = false;
    if (!first) sb.append(", ");
    sb.append("activeThreads:");
    sb.append(this.activeThreads);
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

  private static class ThreadEntityStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ThreadEntityStandardScheme getScheme() {
      return new ThreadEntityStandardScheme();
    }
  }

  private static class ThreadEntityStandardScheme extends org.apache.thrift.scheme.StandardScheme<ThreadEntity> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, ThreadEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // PEAK_THREADS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.peakThreads = iprot.readI64();
              struct.setPeakThreadsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // DAEMON_THREADS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.daemonThreads = iprot.readI64();
              struct.setDaemonThreadsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL_THREADS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.totalThreads = iprot.readI64();
              struct.setTotalThreadsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ACTIVE_THREADS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.activeThreads = iprot.readI64();
              struct.setActiveThreadsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, ThreadEntity struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(PEAK_THREADS_FIELD_DESC);
      oprot.writeI64(struct.peakThreads);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(DAEMON_THREADS_FIELD_DESC);
      oprot.writeI64(struct.daemonThreads);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_THREADS_FIELD_DESC);
      oprot.writeI64(struct.totalThreads);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ACTIVE_THREADS_FIELD_DESC);
      oprot.writeI64(struct.activeThreads);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class ThreadEntityTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public ThreadEntityTupleScheme getScheme() {
      return new ThreadEntityTupleScheme();
    }
  }

  private static class ThreadEntityTupleScheme extends org.apache.thrift.scheme.TupleScheme<ThreadEntity> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, ThreadEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetPeakThreads()) {
        optionals.set(0);
      }
      if (struct.isSetDaemonThreads()) {
        optionals.set(1);
      }
      if (struct.isSetTotalThreads()) {
        optionals.set(2);
      }
      if (struct.isSetActiveThreads()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetPeakThreads()) {
        oprot.writeI64(struct.peakThreads);
      }
      if (struct.isSetDaemonThreads()) {
        oprot.writeI64(struct.daemonThreads);
      }
      if (struct.isSetTotalThreads()) {
        oprot.writeI64(struct.totalThreads);
      }
      if (struct.isSetActiveThreads()) {
        oprot.writeI64(struct.activeThreads);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, ThreadEntity struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.peakThreads = iprot.readI64();
        struct.setPeakThreadsIsSet(true);
      }
      if (incoming.get(1)) {
        struct.daemonThreads = iprot.readI64();
        struct.setDaemonThreadsIsSet(true);
      }
      if (incoming.get(2)) {
        struct.totalThreads = iprot.readI64();
        struct.setTotalThreadsIsSet(true);
      }
      if (incoming.get(3)) {
        struct.activeThreads = iprot.readI64();
        struct.setActiveThreadsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

