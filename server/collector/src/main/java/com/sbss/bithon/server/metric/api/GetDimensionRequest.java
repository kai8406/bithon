package com.sbss.bithon.server.metric.api;

import com.sbss.bithon.server.metric.storage.DimensionCondition;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/7 5:29 下午
 */
@Data
@Validated
public class GetDimensionRequest {

    @NotEmpty
    private String startTimeISO8601;

    @NotEmpty
    private String endTimeISO8601;

    @NotNull
    private String dataSource;

    @Valid
    private Collection<DimensionCondition> conditions = Collections.emptyList();

    @NotNull
    private String dimension;
}
