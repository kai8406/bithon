package com.sbss.bithon.agent.plugin.thread.threadpool;

import com.sbss.bithon.agent.boot.aop.AbstractInterceptor;
import com.sbss.bithon.agent.boot.aop.AopContext;

import java.util.concurrent.ForkJoinPool;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/25 11:15 下午
 */
public class ForkJoinPoolTryTerminate extends AbstractInterceptor {

    @Override
    public void onMethodLeave(AopContext aopContext) {
        ThreadPoolMetricsCollector collector = ThreadPoolMetricsCollector.getInstance();
        if (collector != null) {
            ForkJoinPool pool = aopContext.castTargetAs();
            collector.deleteThreadPool(pool);
        }
    }
}
