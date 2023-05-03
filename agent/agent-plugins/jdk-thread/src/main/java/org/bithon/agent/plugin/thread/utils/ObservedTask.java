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

package org.bithon.agent.plugin.thread.utils;

import org.bithon.agent.observability.tracing.context.ITraceSpan;
import org.bithon.agent.observability.tracing.context.TraceContextHolder;
import org.bithon.agent.plugin.thread.metrics.ThreadPoolMetricRegistry;
import org.bithon.component.commons.tracing.Tags;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author frank.chen021@outlook.com
 * @date 12/5/22 8:57 PM
 */
public class ObservedTask implements Runnable {
    /**
     * The executor that runs the runnable object
     */
    private final ThreadPoolExecutor executor;
    private final Runnable task;
    private final ITraceSpan taskSpan;

    /**
     * @param taskSpan can be NULL
     */
    public ObservedTask(ThreadPoolExecutor executor,
                        Runnable task,
                        ITraceSpan taskSpan) {
        this.executor = executor;
        this.task = task;
        this.taskSpan = taskSpan;
    }

    public Runnable getTask() {
        return task;
    }

    @Override
    public void run() {
        if (this.taskSpan == null) {
            runWithoutTracing();
        } else {
            runWithTracing();
        }
    }

    private void runWithTracing() {
        Throwable exception = null;

        // Setup context on current thread
        TraceContextHolder.set(taskSpan.context());

        taskSpan.start();

        try {
            task.run();
        } catch (Throwable e) {
            exception = e;
            throw e;
        } finally {
            // Set the thread at the end because the thread name might be updated in the users' runnable
            Thread currentThread = Thread.currentThread();
            taskSpan.tag(Tags.Thread.NAME, currentThread.getName())
                    .tag(Tags.Thread.ID, currentThread.getId())
                    .tag(exception)
                    .finish();
            taskSpan.context().finish();

            // Clear context on current thread
            TraceContextHolder.remove();

            ThreadPoolMetricRegistry.getInstance().addRunCount(executor,
                                                               taskSpan.endTime() - taskSpan.startTime(),
                                                               exception != null);
        }
    }

    private void runWithoutTracing() {
        boolean hasException = false;

        long millis = System.currentTimeMillis();
        long nanos = System.nanoTime();

        try {
            task.run();
        } catch (Exception e) {
            hasException = true;
            throw e;
        } finally {
            ThreadPoolMetricRegistry.getInstance().addRunCount(executor,
                                                               (System.nanoTime() - nanos) / 1000L + (System.currentTimeMillis() - millis) * 1000,
                                                               hasException);
        }
    }
}
