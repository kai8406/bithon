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
import lombok.Data;
import org.bithon.component.commons.utils.HumanReadableDuration;
import org.bithon.component.commons.utils.HumanReadableDurationConstraint;
import org.bithon.server.alerting.common.model.AlertRule;
import org.bithon.server.alerting.manager.biz.CommandArgs;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2020/12/31
 */
@Data
public class CreateAlertRequest {

    /**
     * check if target application exists before create an alert for that application
     */
    private boolean checkApplication = false;

    /**
     * Optional.
     * Unique id of alert object
     */
    private String id;

    private String appName;

    @NotEmpty
    private String name;

    /**
     * in minute
     */
    @Min(1)
    @Max(60)
    private int evaluationInterval = 1;

    @JsonProperty("for")
    @HumanReadableDurationConstraint(min = "1m", max = "60m")
    private HumanReadableDuration forDuration = AlertRule.DEFAULT_FOR_DURATION;

    @NotEmpty
    private String expr;

    @Valid
    @NotNull
    private List<String> notifications;

    public AlertRule toAlert() {
        AlertRule alertRule = new AlertRule();
        alertRule.setId(this.id);
        alertRule.setAppName(this.appName == null ? "" : this.appName.trim());
        alertRule.setExpr(this.expr.trim());
        alertRule.setName(this.name.trim());
        alertRule.setNotifications(this.notifications);
        alertRule.setForDuration(this.forDuration);
        alertRule.setEvaluationInterval(this.evaluationInterval);
        alertRule.setEnabled(true);
        return alertRule;
    }

    public CommandArgs toCommandArgs() {
        return CommandArgs.builder().checkApplicationExist(checkApplication).build();
    }
}
