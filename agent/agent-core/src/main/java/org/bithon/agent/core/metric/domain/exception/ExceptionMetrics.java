/*
 *    Copyright 2020 bithon.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.bithon.agent.core.metric.domain.exception;

import org.bithon.agent.core.metric.model.IMetric;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/16 8:21 下午
 */
public class ExceptionMetrics implements IMetric {
    // dimension
    private final String uri;
    private final String exceptionId;
    private final String exceptionClass;
    private final String message;
    private final String stackTrace;
    // counter
    private int count = 0;

    public ExceptionMetrics(String uri,
                            String exceptionClass,
                            String message,
                            String stackTrace) {
        this.uri = uri;
        this.exceptionId = md5(stackTrace) + md5(message);
        this.message = message;
        this.exceptionClass = exceptionClass;
        this.stackTrace = stackTrace;
    }

    public static ExceptionMetrics fromException(String uri, Throwable exception) {
        return new ExceptionMetrics(uri,
                                    exception.getClass().getSimpleName(),
                                    exception.getMessage(),
                                    getFullStack(exception.getStackTrace()));
    }

    private static String md5(String stack) {
        if (stack == null) {
            return null;
        }
        try {
            byte[] byteArray = stack.getBytes(StandardCharsets.UTF_8);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteArray);
            byte[] bytes = md5.digest();
            final char[] digits = "0123456789ABCDEF".toCharArray();
            StringBuilder ret = new StringBuilder(bytes.length * 2);
            for (byte aByte : bytes) {
                ret.append(digits[(aByte >> 4) & 0x0f]);
                ret.append(digits[aByte & 0x0f]);
            }
            return ret.toString();
        } catch (Exception ignored) {
        }
        return null;
    }

    private static String getFullStack(StackTraceElement[] stacks) {
        StringBuilder sb = new StringBuilder();
        if (stacks != null && stacks.length > 0) {
            for (StackTraceElement msg : stacks) {
                sb.append(msg.toString()).append("\r\n");
            }
        }
        return sb.toString();
    }

    public String getUri() {
        return uri;
    }

    public String getExceptionId() {
        return exceptionId;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public int getCount() {
        return count;
    }

    public String getMessage() {
        return message;
    }

    public void incrCount() {
        count++;
    }
}