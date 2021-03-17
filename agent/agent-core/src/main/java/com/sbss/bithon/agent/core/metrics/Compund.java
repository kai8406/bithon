package com.sbss.bithon.agent.core.metrics;

/**
 * It's a compound metric which holds counter, min and max value
 * <p>
 * I have not come up with a nice name.
 *
 * @author frank.chen021@outlook.com
 * @date 2021-03-16
 */
public class Compund {

    private Counter counter = new Counter();
    private Max max = new Max();
    private Min min = new Min();

    public void update(long value) {
        counter.update(value);
        max.update(value);
        min.update(value);
    }

    public Counter getCounter() {
        return counter;
    }

    public Max getMax() {
        return max;
    }

    public Min getMin() {
        return min;
    }
}
