package com.sbss.bithon.server.metric.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbss.bithon.server.metric.input.InputRow;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/19
 */
public class OrFilter implements IFilter {

    @NotNull
    private final List<IFilter> filters;

    public OrFilter(@JsonProperty("filters") List<IFilter> filters) {
        this.filters = filters;
    }

    @Override
    public boolean shouldInclude(InputRow inputRow) {
        for (IFilter filter : this.filters) {
            if (filter.shouldInclude(inputRow)) {
                return true;
            }
        }
        return false;
    }
}