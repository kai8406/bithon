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

package org.bithon.server.web.service.agent.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.bithon.component.commons.exception.HttpMappableException;
import org.bithon.component.commons.utils.Preconditions;
import org.bithon.component.commons.utils.StringUtils;
import org.bithon.server.agent.controller.config.AgentControllerConfig;
import org.bithon.server.discovery.client.DiscoveredServiceInvoker;
import org.bithon.server.discovery.declaration.controller.IAgentControllerApi;
import org.bithon.server.storage.setting.ISettingReader;
import org.bithon.server.storage.setting.ISettingStorage;
import org.bithon.server.storage.setting.ISettingWriter;
import org.bithon.server.web.service.WebServiceModuleEnabler;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Frank Chen
 * @date 26/1/24 1:03 pm
 */
@CrossOrigin
@RestController
@Conditional(WebServiceModuleEnabler.class)
public class AgentConfigurationApi {

    private final ISettingStorage settingStorage;
    private final ObjectMapper objectMapper;
    private final AgentControllerConfig agentControllerConfig;
    private final IAgentControllerApi agentControllerApi;

    public AgentConfigurationApi(ISettingStorage settingStorage,
                                 ObjectMapper objectMapper,
                                 AgentControllerConfig agentControllerConfig,
                                 DiscoveredServiceInvoker discoveredServiceInvoker) {
        this.settingStorage = settingStorage;
        this.objectMapper = objectMapper;
        this.agentControllerConfig = agentControllerConfig;
        this.agentControllerApi = discoveredServiceInvoker.createBroadcastApi(IAgentControllerApi.class);
    }

    @Data
    public static class GetRequest {
        @NotNull
        private String appName;

        /**
         * Optional
         */
        private String environment;

        private String format = "default";
    }

    @PostMapping("/api/agent/configuration/get")
    public List<ISettingReader.SettingEntry> getConfiguration(@RequestBody GetRequest request) {
        List<ISettingReader.SettingEntry> perAppSetting = settingStorage.createReader()
                                                                        .getSettings(request.getAppName().trim());

        if (StringUtils.isBlank(request.getEnvironment())) {
            return perAppSetting;
        }

        List<ISettingReader.SettingEntry> perEnvSetting = settingStorage.createReader()
                                                                        .getSettings(request.getAppName().trim(),
                                                                                     request.getEnvironment().trim());

        perAppSetting.addAll(perEnvSetting);

        return perAppSetting;
    }

    @Data
    public static class AddRequest {
        @NotEmpty
        private String appName;

        private String environment;

        @NotEmpty
        private String name;

        @NotNull
        private String value;

        /**
         * can be NULL, or json/yaml
         * If it's NULL, it's default to json.
         */
        private String format;
    }

    @PostMapping("/api/agent/configuration/add")
    public void addConfiguration(@RequestHeader(value = "token", required = false) String token,
                                 @Validated @RequestBody AddRequest request) {
        String format = request.getFormat() != null ? request.getFormat().trim() : "json";
        Preconditions.checkIfTrue("yaml".equals(format) || "json".equals(format),
                                  "Invalid format[%s] given. Only json or yaml is supported.",
                                  format);

        ObjectMapper mapper;
        if ("json".equals(format)) {
            mapper = new ObjectMapper();
        } else {
            mapper = new ObjectMapper(new YAMLFactory());
        }
        try {
            mapper.readTree(request.getValue());
        } catch (JsonProcessingException e) {
            throw new HttpMappableException(HttpStatus.BAD_REQUEST.value(), "The 'value' is not valid format of %s", format);
        }

        String application = request.getAppName().trim();
        String environment = request.getEnvironment() == null ? "" : request.getEnvironment().trim();
        String settingName = request.getName().trim();
        String settingVal = request.getValue().trim();

        this.agentControllerConfig.getPermission()
                                  .verifyPermission(objectMapper, application, getUserOrToken(token));

        Object existingSetting = settingStorage.createReader().getSetting(application, environment, settingName);
        Preconditions.checkIfTrue(existingSetting == null, "Setting already exist.");

        ISettingWriter writer = settingStorage.createWriter();
        writer.addSetting(application, environment, settingName, settingVal, format);

        // Notify agent controller about the change
        notifyConfigurationChange(request.appName, request.environment);
    }

    @PostMapping("/api/agent/configuration/update")
    public void updateConfiguration(@RequestHeader(value = "token", required = false) String token,
                                    @Validated @RequestBody AddRequest request) {
        String format = request.getFormat() != null ? request.getFormat().trim() : "json";
        Preconditions.checkIfTrue("yaml".equals(format) || "json".equals(format),
                                  "Invalid format[%s] given. Only json or yaml is supported.",
                                  format);

        ObjectMapper mapper;
        if ("json".equals(format)) {
            mapper = new ObjectMapper();
        } else {
            mapper = new ObjectMapper(new YAMLFactory());
        }
        try {
            mapper.readTree(request.getValue());
        } catch (JsonProcessingException e) {
            throw new HttpMappableException(HttpStatus.BAD_REQUEST.value(), "The 'value' is not valid format of %s", format);
        }

        String application = request.getAppName().trim();
        String environment = request.getEnvironment() == null ? "" : request.getEnvironment().trim();
        String settingName = request.getName().trim();
        String settingVal = request.getValue().trim();

        this.agentControllerConfig.getPermission()
                                  .verifyPermission(objectMapper, application, getUserOrToken(token));

        Object existingSetting = settingStorage.createReader().getSetting(application, environment, settingName);
        Preconditions.checkIfTrue(existingSetting != null, "Setting does not exist.");

        ISettingWriter writer = settingStorage.createWriter();
        writer.updateSetting(application, environment, settingName, settingVal, format);

        // Notify agent controller about the change
        notifyConfigurationChange(request.appName, request.environment);
    }

    @Data
    public static class DeleteRequest {
        @NotEmpty
        private String appName;

        @NotEmpty
        private String name;

        private String environment;
    }

    @PostMapping("/api/agent/configuration/delete")
    public void deleteConfiguration(@RequestHeader(value = "token", required = false) String token,
                                    @Validated @RequestBody DeleteRequest request) {
        this.agentControllerConfig.getPermission()
                                  .verifyPermission(objectMapper, request.getAppName(), getUserOrToken(token));

        ISettingWriter writer = settingStorage.createWriter();
        writer.deleteSetting(request.getAppName(), request.getEnvironment() == null ? "" : request.getEnvironment().trim(), request.getName());

        // Notify agent controller about the change
        notifyConfigurationChange(request.appName, request.environment);
    }

    private void notifyConfigurationChange(String appName, String env) {
        this.agentControllerApi.updateAgentSetting(appName, env);
    }

    private String getUserOrToken(String token) {
        Authentication authentication = SecurityContextHolder.getContext() == null ? null : SecurityContextHolder.getContext().getAuthentication();
        String principal = authentication == null ? null : (String) authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)) {
            if (token == null) {
                throw new HttpMappableException(HttpStatus.BAD_REQUEST.value(),
                                                "No user or token provided for authorization to perform this operation.");
            }

            // Use token-based authorization
            return token;
        }

        // Use user-based-authorization
        return principal;
    }
}
