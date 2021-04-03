package com.sbss.bithon.server.metric.aggregator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbss.bithon.server.metric.DataSourceSchema;
import com.sbss.bithon.server.metric.typing.IValueType;
import com.sbss.bithon.server.metric.typing.LongValueType;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author frank.chen021@outlook.com
 * @date 2020/11/30 5:38 下午
 */
public class LongSumMetricSpec implements IMetricSpec {

    @Getter
    private final String name;

    @Getter
    private final String displayText;

    @Getter
    private final String unit;

    @Getter
    private final boolean visible;

    @JsonCreator
    public LongSumMetricSpec(@JsonProperty("name") @NotNull String name,
                             @JsonProperty("displayText") @NotNull String displayText,
                             @JsonProperty("unit") @NotNull String unit,
                             @JsonProperty("visible") @Nullable Boolean visible) {
        this.name = name;
        this.displayText = displayText;
        this.unit = unit;
        this.visible = visible == null ? true : visible;
    }

    @JsonIgnore
    @Override
    public String getType() {
        return IMetricSpec.LONG_SUM;
    }

    @Override
    public IValueType getValueType() {
        return LongValueType.INSTANCE;
    }

    @Override
    public void setOwner(DataSourceSchema dataSource) {
    }

    @Override
    public String validate(Object input) {
        return null;
    }

    @Override
    public <T> T accept(IMetricSpecVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LongSumMetricSpec) {
            return this.name.equals(((LongSumMetricSpec) obj).name);
        } else {
            return false;
        }
    }
}