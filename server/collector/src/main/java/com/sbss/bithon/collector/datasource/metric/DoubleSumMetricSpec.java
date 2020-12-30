package com.sbss.bithon.collector.datasource.metric;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbss.bithon.collector.datasource.DataSourceSchema;
import com.sbss.bithon.collector.datasource.typing.DoubleValueType;
import com.sbss.bithon.collector.datasource.typing.IValueType;
import lombok.Getter;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author frank.chen021@outlook.com
 * @date 2020/12/23
 */
public class DoubleSumMetricSpec implements ISimpleMetricSpec {

    @Getter
    private final String name;

    @Getter
    private final String displayText;

    @Getter
    private final String unit;

    @Getter
    private final String field;

    @Getter
    private final boolean visible;

    @JsonCreator
    public DoubleSumMetricSpec(@JsonProperty("name") @NotNull String name,
                               @JsonProperty("displayText") @NotNull String displayText,
                               @JsonProperty("unit") @NotNull String unit,
                               @JsonProperty("field") @NotNull String field,
                               @JsonProperty("visible") @Nullable Boolean visible) {
        this.name = name;
        this.displayText = displayText;
        this.unit = unit;
        this.field = field;
        this.visible = visible == null || visible;
    }

    @JsonIgnore
    @Override
    public String getType() {
        return IMetricSpec.DOUBLE_SUM;
    }

    @Override
    public IValueType getValueType() {
        return DoubleValueType.INSTANCE;
    }

    @Override
    public void setOwner(DataSourceSchema dataSource) {

    }

    @Override
    public String validate(Object input) {
        return null;
    }

    @Override
    public <T> T accept(IMetricSpecVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DoubleSumMetricSpec) {
            return this.name.equals(((DoubleSumMetricSpec) obj).name);
        } else {
            return false;
        }
    }
}
