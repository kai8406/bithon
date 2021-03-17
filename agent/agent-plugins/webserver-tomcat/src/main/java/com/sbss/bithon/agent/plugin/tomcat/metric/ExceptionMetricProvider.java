package com.sbss.bithon.agent.plugin.tomcat.metric;

import com.sbss.bithon.agent.core.context.AppInstance;
import com.sbss.bithon.agent.core.context.InterceptorContext;
import com.sbss.bithon.agent.core.dispatcher.IMessageConverter;
import com.sbss.bithon.agent.core.metrics.IMetricProvider;

import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author frankchen
 */
public class ExceptionMetricProvider implements IMetricProvider {
    private static final int ERROR_SOURCE_TYPE_TOMCAT = 1;

    private final long earliestRecordTimestamp = 0;
    private final Queue<ClientException> exceptionStorage = new ConcurrentLinkedDeque<>();
    private long latestRecordTimestamp = 0;

    public void update(Throwable e) {
        if (null != e) {
            exceptionStorage.offer(new ClientException(System.currentTimeMillis(),
                                                       (String) InterceptorContext.get("uri"),
                                                       e));
            latestRecordTimestamp = System.currentTimeMillis();
        }
    }

    @Override
    public boolean isEmpty() {
        return exceptionStorage.isEmpty();
    }

    @Override
    public List<Object> buildMessages(IMessageConverter messageConverter,
                                      AppInstance appInstance,
                                      int interval,
                                      long timestamp) {

//        List<FailureMessageDetailEntity> failureMessageDetailEntities = new ArrayList<>();
//        ClientException clientException;
//        long buildTimestamp = System.currentTimeMillis();
//        int occurTimes = 0;
//        Map<String, FailureMessageDetailEntity> entityMap = new HashMap<>();
//        if (earliestRecordTimestamp > buildTimestamp || latestRecordTimestamp < buildTimestamp - (interval * 1000)) {
//            // no record in time_window, refresh the earliestTimestamp & latestTimestamp
//            earliestRecordTimestamp = buildTimestamp;
//            latestRecordTimestamp = buildTimestamp;
//        } else {
//            do {
//                clientException = exceptionStorage.poll();
//                if (null == clientException) {
//                    break;
//                }
//
//                occurTimes++;
//                Throwable t = clientException.getRootException();
//                ExceptionEntity rootException = getExceptionEntityFromThrowable(t);
//                // List<ExceptionEntity> causedByException = new LinkedList<>();
//                // deepSearchCausedByExceptions(causedByException, t);
//                FailureMessageDetailEntity entity = null;
//                if ((entity = entityMap.get(clientException.getUri() + rootException.getExceptionId() +
//                                                rootException.getMessageId())) == null) {
//                    entity = new FailureMessageDetailEntity(clientException.getTimestamp(), rootException, null);
//                    entity.setUrl(clientException.getUri());
//                    entityMap.put(clientException.getUri() + rootException.getExceptionId() +
//                                      rootException.getMessageId(),
//                                  entity);
//                    failureMessageDetailEntities.add(entity);
//                }
//            } while (clientException.timestamp < buildTimestamp);
//
//            earliestRecordTimestamp = buildTimestamp;
//        }
//
//        return Collections.singletonList(new FailureMessageEntity(appName,
//                                                                  ipAddress,
//                                                                  port,
//                                                                  failureMessageDetailEntities,
//                                                                  occurTimes,
//                                                                  null,
//                                                                  ERROR_SOURCE_TYPE_TOMCAT));
        return Collections.emptyList();
    }

    private static class ClientException {
        private final long timestamp;
        private final Throwable rootException;
        private String uri;

        ClientException(long occurTimestamp, String uri, Throwable throwable) {
            this.timestamp = occurTimestamp;
            this.rootException = throwable;
            this.uri = uri;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ClientException that = (ClientException) o;

            return timestamp == that.timestamp && rootException.equals(that.rootException);
        }

        @Override
        public int hashCode() {
            int result = (int) (timestamp ^ (timestamp >>> 32));
            result = 31 * result + rootException.hashCode();
            return result;
        }

        long getTimestamp() {
            return timestamp;
        }

        Throwable getRootException() {
            return rootException;
        }
    }

//    private ExceptionEntity getExceptionEntityFromThrowable(Throwable t) {
//        return new ExceptionEntity(t.getClass().getName(),
//                                   t.getMessage(),
//                                   (t.getStackTrace() == null ||
//                                       t.getStackTrace().length < 1) ? null : t.getStackTrace()[0].toString());
//    }
//
//    private void deepSearchCausedByExceptions(List<ExceptionEntity> causedByException,
//                                              Throwable t) {
//        if (null != t.getCause()) {
//            Throwable cause = t.getCause();
//            causedByException.add(getExceptionEntityFromThrowable(cause));
//            deepSearchCausedByExceptions(causedByException, cause);
//        }
//    }
}
