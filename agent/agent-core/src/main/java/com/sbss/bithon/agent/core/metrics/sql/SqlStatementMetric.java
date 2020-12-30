package com.sbss.bithon.agent.core.metrics.sql;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/5 9:20 下午
 */
public class SqlStatementMetric {
    private final String driverType;
    private final String sql;
    private final AtomicLong executeCount = new AtomicLong(0);
    private final AtomicLong executeErrorCount = new AtomicLong(0);
    private final AtomicLong totalTime = new AtomicLong(0);
    private final LongAccumulator lastTime = new LongAccumulator(Long::max, 0L);
    private final LongAccumulator maxTimeSpan = new LongAccumulator(Long::max, 0L);
    private final AtomicLong effectedRowCount = new AtomicLong();
    private final AtomicLong fetchedRowCount = new AtomicLong();
    private final LongAccumulator batchSizeMax = new LongAccumulator(Long::max, 0L);
    private final AtomicLong batchSizeTotal = new AtomicLong();
    private final LongAccumulator concurrentMax = new LongAccumulator(Long::max, 0L);

    public SqlStatementMetric(String driverType, String sql) {
        this.driverType = driverType;
        this.sql = sql;
    }

    public SqlStatementMetric add(long executeNum,
                                  long executeErrorNum,
                                  long executeTime) {
        executeCount.addAndGet(executeNum);
        executeErrorCount.addAndGet(executeErrorNum);
        totalTime.addAndGet(executeTime);
        lastTime.accumulate(System.currentTimeMillis());
        maxTimeSpan.accumulate(executeTime);

        /*
         * effectedRowCount.addAndGet(appender.effectedRowCount.get());
         * fetchedRowCount.addAndGet(appender.fetchedRowCount.get());
         * batchSizeMax.accumulate(appender.batchSizeMax.get());
         * batchSizeTotal.addAndGet(appender.batchSizeTotal.get());
         * concurrentMax.accumulate(appender.concurrentMax.get());
         */

        return this;
    }
}
