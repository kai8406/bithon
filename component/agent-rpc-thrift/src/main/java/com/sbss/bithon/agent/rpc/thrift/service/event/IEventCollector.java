/**
 * Autogenerated by Thrift Compiler (0.14.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.sbss.bithon.agent.rpc.thrift.service.event;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.14.1)", date = "2021-03-20")
public class IEventCollector {

  public interface Iface {

    public void sendEvent(com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header, ThriftEventMessage body) throws org.apache.thrift.TException;

  }

  public interface AsyncIface {

    public void sendEvent(com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header, ThriftEventMessage body, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws org.apache.thrift.TException;

  }

  public static class Client extends org.apache.thrift.TServiceClient implements Iface {
    public static class Factory implements org.apache.thrift.TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(org.apache.thrift.protocol.TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(org.apache.thrift.protocol.TProtocol prot)
    {
      super(prot, prot);
    }

    public Client(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TProtocol oprot) {
      super(iprot, oprot);
    }

    public void sendEvent(com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header, ThriftEventMessage body) throws org.apache.thrift.TException
    {
      send_sendEvent(header, body);
    }

    public void send_sendEvent(com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header, ThriftEventMessage body) throws org.apache.thrift.TException
    {
      sendEvent_args args = new sendEvent_args();
      args.setHeader(header);
      args.setBody(body);
      sendBaseOneway("sendEvent", args);
    }

  }
  public static class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
    public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
      private org.apache.thrift.async.TAsyncClientManager clientManager;
      private org.apache.thrift.protocol.TProtocolFactory protocolFactory;
      public Factory(org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.async.TAsyncClientManager clientManager, org.apache.thrift.transport.TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void sendEvent(com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header, ThriftEventMessage body, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws org.apache.thrift.TException {
      checkReady();
      sendEvent_call method_call = new sendEvent_call(header, body, resultHandler, this, ___protocolFactory, ___transport);
      this.___currentMethod = method_call;
      ___manager.call(method_call);
    }

    public static class sendEvent_call extends org.apache.thrift.async.TAsyncMethodCall<Void> {
      private com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header;
      private ThriftEventMessage body;
      public sendEvent_call(com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header, ThriftEventMessage body, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler, org.apache.thrift.async.TAsyncClient client, org.apache.thrift.protocol.TProtocolFactory protocolFactory, org.apache.thrift.transport.TNonblockingTransport transport) throws org.apache.thrift.TException {
        super(client, protocolFactory, transport, resultHandler, true);
        this.header = header;
        this.body = body;
      }

      public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
        prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("sendEvent", org.apache.thrift.protocol.TMessageType.ONEWAY, 0));
        sendEvent_args args = new sendEvent_args();
        args.setHeader(header);
        args.setBody(body);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public Void getResult() throws org.apache.thrift.TException {
        if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
          throw new java.lang.IllegalStateException("Method call not finished!");
        }
        org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(getFrameBuffer().array());
        org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return null;
      }
    }

  }

  public static class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements org.apache.thrift.TProcessor {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, java.util.Map<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> java.util.Map<java.lang.String,  org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> getProcessMap(java.util.Map<java.lang.String, org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("sendEvent", new sendEvent());
      return processMap;
    }

    public static class sendEvent<I extends Iface> extends org.apache.thrift.ProcessFunction<I, sendEvent_args> {
      public sendEvent() {
        super("sendEvent");
      }

      public sendEvent_args getEmptyArgsInstance() {
        return new sendEvent_args();
      }

      protected boolean isOneway() {
        return true;
      }

      @Override
      protected boolean rethrowUnhandledExceptions() {
        return false;
      }

      public org.apache.thrift.TBase getResult(I iface, sendEvent_args args) throws org.apache.thrift.TException {
        iface.sendEvent(args.header, args.body);
        return null;
      }
    }

  }

  public static class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
    private static final org.slf4j.Logger _LOGGER = org.slf4j.LoggerFactory.getLogger(AsyncProcessor.class.getName());
    public AsyncProcessor(I iface) {
      super(iface, getProcessMap(new java.util.HashMap<java.lang.String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
    }

    protected AsyncProcessor(I iface, java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends AsyncIface> java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase,?>> getProcessMap(java.util.Map<java.lang.String,  org.apache.thrift.AsyncProcessFunction<I, ? extends  org.apache.thrift.TBase, ?>> processMap) {
      processMap.put("sendEvent", new sendEvent());
      return processMap;
    }

    public static class sendEvent<I extends AsyncIface> extends org.apache.thrift.AsyncProcessFunction<I, sendEvent_args, Void> {
      public sendEvent() {
        super("sendEvent");
      }

      public sendEvent_args getEmptyArgsInstance() {
        return new sendEvent_args();
      }

      public org.apache.thrift.async.AsyncMethodCallback<Void> getResultHandler(final org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer fb, final int seqid) {
        final org.apache.thrift.AsyncProcessFunction fcall = this;
        return new org.apache.thrift.async.AsyncMethodCallback<Void>() { 
          public void onComplete(Void o) {
          }
          public void onError(java.lang.Exception e) {
            if (e instanceof org.apache.thrift.transport.TTransportException) {
              _LOGGER.error("TTransportException inside handler", e);
              fb.close();
            } else {
              _LOGGER.error("Exception inside oneway handler", e);
            }
          }
        };
      }

      protected boolean isOneway() {
        return true;
      }

      public void start(I iface, sendEvent_args args, org.apache.thrift.async.AsyncMethodCallback<Void> resultHandler) throws org.apache.thrift.TException {
        iface.sendEvent(args.header, args.body,resultHandler);
      }
    }

  }

  public static class sendEvent_args implements org.apache.thrift.TBase<sendEvent_args, sendEvent_args._Fields>, java.io.Serializable, Cloneable, Comparable<sendEvent_args>   {
    private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("sendEvent_args");

    private static final org.apache.thrift.protocol.TField HEADER_FIELD_DESC = new org.apache.thrift.protocol.TField("header", org.apache.thrift.protocol.TType.STRUCT, (short)1);
    private static final org.apache.thrift.protocol.TField BODY_FIELD_DESC = new org.apache.thrift.protocol.TField("body", org.apache.thrift.protocol.TType.STRUCT, (short)2);

    private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new sendEvent_argsStandardSchemeFactory();
    private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new sendEvent_argsTupleSchemeFactory();

    public @org.apache.thrift.annotation.Nullable com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header; // required
    public @org.apache.thrift.annotation.Nullable ThriftEventMessage body; // required

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements org.apache.thrift.TFieldIdEnum {
      HEADER((short)1, "header"),
      BODY((short)2, "body");

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
          case 1: // HEADER
            return HEADER;
          case 2: // BODY
            return BODY;
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
    public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
    static {
      java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.HEADER, new org.apache.thrift.meta_data.FieldMetaData("header", org.apache.thrift.TFieldRequirementType.REQUIRED, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.sbss.bithon.agent.rpc.thrift.service.MessageHeader.class)));
      tmpMap.put(_Fields.BODY, new org.apache.thrift.meta_data.FieldMetaData("body", org.apache.thrift.TFieldRequirementType.REQUIRED, 
          new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, ThriftEventMessage.class)));
      metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
      org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(sendEvent_args.class, metaDataMap);
    }

    public sendEvent_args() {
    }

    public sendEvent_args(
      com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header,
      ThriftEventMessage body)
    {
      this();
      this.header = header;
      this.body = body;
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public sendEvent_args(sendEvent_args other) {
      if (other.isSetHeader()) {
        this.header = new com.sbss.bithon.agent.rpc.thrift.service.MessageHeader(other.header);
      }
      if (other.isSetBody()) {
        this.body = new ThriftEventMessage(other.body);
      }
    }

    public sendEvent_args deepCopy() {
      return new sendEvent_args(this);
    }

    @Override
    public void clear() {
      this.header = null;
      this.body = null;
    }

    @org.apache.thrift.annotation.Nullable
    public com.sbss.bithon.agent.rpc.thrift.service.MessageHeader getHeader() {
      return this.header;
    }

    public sendEvent_args setHeader(@org.apache.thrift.annotation.Nullable com.sbss.bithon.agent.rpc.thrift.service.MessageHeader header) {
      this.header = header;
      return this;
    }

    public void unsetHeader() {
      this.header = null;
    }

    /** Returns true if field header is set (has been assigned a value) and false otherwise */
    public boolean isSetHeader() {
      return this.header != null;
    }

    public void setHeaderIsSet(boolean value) {
      if (!value) {
        this.header = null;
      }
    }

    @org.apache.thrift.annotation.Nullable
    public ThriftEventMessage getBody() {
      return this.body;
    }

    public sendEvent_args setBody(@org.apache.thrift.annotation.Nullable ThriftEventMessage body) {
      this.body = body;
      return this;
    }

    public void unsetBody() {
      this.body = null;
    }

    /** Returns true if field body is set (has been assigned a value) and false otherwise */
    public boolean isSetBody() {
      return this.body != null;
    }

    public void setBodyIsSet(boolean value) {
      if (!value) {
        this.body = null;
      }
    }

    public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
      switch (field) {
      case HEADER:
        if (value == null) {
          unsetHeader();
        } else {
          setHeader((com.sbss.bithon.agent.rpc.thrift.service.MessageHeader)value);
        }
        break;

      case BODY:
        if (value == null) {
          unsetBody();
        } else {
          setBody((ThriftEventMessage)value);
        }
        break;

      }
    }

    @org.apache.thrift.annotation.Nullable
    public java.lang.Object getFieldValue(_Fields field) {
      switch (field) {
      case HEADER:
        return getHeader();

      case BODY:
        return getBody();

      }
      throw new java.lang.IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new java.lang.IllegalArgumentException();
      }

      switch (field) {
      case HEADER:
        return isSetHeader();
      case BODY:
        return isSetBody();
      }
      throw new java.lang.IllegalStateException();
    }

    @Override
    public boolean equals(java.lang.Object that) {
      if (that instanceof sendEvent_args)
        return this.equals((sendEvent_args)that);
      return false;
    }

    public boolean equals(sendEvent_args that) {
      if (that == null)
        return false;
      if (this == that)
        return true;

      boolean this_present_header = true && this.isSetHeader();
      boolean that_present_header = true && that.isSetHeader();
      if (this_present_header || that_present_header) {
        if (!(this_present_header && that_present_header))
          return false;
        if (!this.header.equals(that.header))
          return false;
      }

      boolean this_present_body = true && this.isSetBody();
      boolean that_present_body = true && that.isSetBody();
      if (this_present_body || that_present_body) {
        if (!(this_present_body && that_present_body))
          return false;
        if (!this.body.equals(that.body))
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      int hashCode = 1;

      hashCode = hashCode * 8191 + ((isSetHeader()) ? 131071 : 524287);
      if (isSetHeader())
        hashCode = hashCode * 8191 + header.hashCode();

      hashCode = hashCode * 8191 + ((isSetBody()) ? 131071 : 524287);
      if (isSetBody())
        hashCode = hashCode * 8191 + body.hashCode();

      return hashCode;
    }

    @Override
    public int compareTo(sendEvent_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;

      lastComparison = java.lang.Boolean.compare(isSetHeader(), other.isSetHeader());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetHeader()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.header, other.header);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      lastComparison = java.lang.Boolean.compare(isSetBody(), other.isSetBody());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetBody()) {
        lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.body, other.body);
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
      java.lang.StringBuilder sb = new java.lang.StringBuilder("sendEvent_args(");
      boolean first = true;

      sb.append("header:");
      if (this.header == null) {
        sb.append("null");
      } else {
        sb.append(this.header);
      }
      first = false;
      if (!first) sb.append(", ");
      sb.append("body:");
      if (this.body == null) {
        sb.append("null");
      } else {
        sb.append(this.body);
      }
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws org.apache.thrift.TException {
      // check for required fields
      if (header == null) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'header' was not present! Struct: " + toString());
      }
      if (body == null) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'body' was not present! Struct: " + toString());
      }
      // check for sub-struct validity
      if (header != null) {
        header.validate();
      }
      if (body != null) {
        body.validate();
      }
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
        read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
      } catch (org.apache.thrift.TException te) {
        throw new java.io.IOException(te);
      }
    }

    private static class sendEvent_argsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public sendEvent_argsStandardScheme getScheme() {
        return new sendEvent_argsStandardScheme();
      }
    }

    private static class sendEvent_argsStandardScheme extends org.apache.thrift.scheme.StandardScheme<sendEvent_args> {

      public void read(org.apache.thrift.protocol.TProtocol iprot, sendEvent_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TField schemeField;
        iprot.readStructBegin();
        while (true)
        {
          schemeField = iprot.readFieldBegin();
          if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
            break;
          }
          switch (schemeField.id) {
            case 1: // HEADER
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.header = new com.sbss.bithon.agent.rpc.thrift.service.MessageHeader();
                struct.header.read(iprot);
                struct.setHeaderIsSet(true);
              } else { 
                org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
              }
              break;
            case 2: // BODY
              if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
                struct.body = new ThriftEventMessage();
                struct.body.read(iprot);
                struct.setBodyIsSet(true);
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

      public void write(org.apache.thrift.protocol.TProtocol oprot, sendEvent_args struct) throws org.apache.thrift.TException {
        struct.validate();

        oprot.writeStructBegin(STRUCT_DESC);
        if (struct.header != null) {
          oprot.writeFieldBegin(HEADER_FIELD_DESC);
          struct.header.write(oprot);
          oprot.writeFieldEnd();
        }
        if (struct.body != null) {
          oprot.writeFieldBegin(BODY_FIELD_DESC);
          struct.body.write(oprot);
          oprot.writeFieldEnd();
        }
        oprot.writeFieldStop();
        oprot.writeStructEnd();
      }

    }

    private static class sendEvent_argsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
      public sendEvent_argsTupleScheme getScheme() {
        return new sendEvent_argsTupleScheme();
      }
    }

    private static class sendEvent_argsTupleScheme extends org.apache.thrift.scheme.TupleScheme<sendEvent_args> {

      @Override
      public void write(org.apache.thrift.protocol.TProtocol prot, sendEvent_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        struct.header.write(oprot);
        struct.body.write(oprot);
      }

      @Override
      public void read(org.apache.thrift.protocol.TProtocol prot, sendEvent_args struct) throws org.apache.thrift.TException {
        org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
        struct.header = new com.sbss.bithon.agent.rpc.thrift.service.MessageHeader();
        struct.header.read(iprot);
        struct.setHeaderIsSet(true);
        struct.body = new ThriftEventMessage();
        struct.body.read(iprot);
        struct.setBodyIsSet(true);
      }
    }

    private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
      return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
    }
  }

}
