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

package org.bithon.server.alerting.manager.api.parameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.bithon.component.commons.utils.HumanReadableDuration;
import org.bithon.server.alerting.common.model.AlertRule;
import org.bithon.server.alerting.manager.biz.CommandArgs;
import org.bithon.server.commons.utils.HumanReadableDurationConstraint;

import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2020/12/31
 */
@Data
public class CreateAlertRuleRequest {

    /**
     * check if target application exists before create an alert for that application
     */
    private boolean checkApplication = false;

    /**
     * Optional.
     * Unique id of alert object
     */
    private String id;

    @NotEmpty
    private String name;

    /**
     * The interval that the alert is evaluated
     */
    @HumanReadableDurationConstraint(min = "1m", max = "24h")
    private HumanReadableDuration every = HumanReadableDuration.DURATION_1_MINUTE;

    /**
     * How many consecutive times the alert expression is evaluated to be true before firing the alert.
     * The max is set to 60 temporarily.
     */
    @JsonProperty("for")
    @Min(1)
    @Max(60)
    private int forTimes = 3;

    @JsonProperty("silence")
    @HumanReadableDurationConstraint(min = "1m", max = "60m")
    private HumanReadableDuration silence = HumanReadableDuration.DURATION_3_MINUTE;

    @NotEmpty
    private String expr;

    @Valid
    @NotEmpty
    private List<String> notifications;

    public AlertRule toAlert() {
        AlertRule alertRule = new AlertRule();
        alertRule.setId(this.id);
        alertRule.setExpr(this.expr.trim());
        alertRule.setName(this.name.trim());
        alertRule.setNotifications(this.notifications);
        alertRule.setForTimes(this.forTimes);
        alertRule.setEvery(this.every);
        alertRule.setSilence(silence);
        alertRule.setEnabled(true);
        return alertRule;
    }

    public CommandArgs toCommandArgs() {
        return CommandArgs.builder().checkApplicationExist(checkApplication).build();
    }
}
