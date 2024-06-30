// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: WebRequestMetrics.proto

package org.bithon.component.brpc.example.protobuf;

public interface WebRequestMetricsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:org.bithon.component.brpc.example.protobuf.WebRequestMetrics)
    org.bithon.shaded.com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string uri = 1;</code>
   * @return The uri.
   */
  String getUri();
  /**
   * <code>string uri = 1;</code>
   * @return The bytes for uri.
   */
  org.bithon.shaded.com.google.protobuf.ByteString
      getUriBytes();

  /**
   * <code>int64 costNanoTime = 2;</code>
   * @return The costNanoTime.
   */
  long getCostNanoTime();

  /**
   * <code>int64 requests = 3;</code>
   * @return The requests.
   */
  long getRequests();

  /**
   * <code>int64 count4xx = 4;</code>
   * @return The count4xx.
   */
  long getCount4Xx();

  /**
   * <code>int64 count5xx = 5;</code>
   * @return The count5xx.
   */
  long getCount5Xx();

  /**
   * <code>int64 requestBytes = 6;</code>
   * @return The requestBytes.
   */
  long getRequestBytes();

  /**
   * <code>int64 responseBytes = 7;</code>
   * @return The responseBytes.
   */
  long getResponseBytes();
}
