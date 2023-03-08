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

package org.bithon.agent.controller.config;


import org.bithon.agent.controller.IAgentController;
import org.bithon.agent.core.config.Configuration;
import org.bithon.agent.core.config.ConfigurationManager;
import org.bithon.component.commons.logging.ILogAdaptor;
import org.bithon.component.commons.logging.LoggerFactory;
import org.bithon.component.commons.security.HashGenerator;
import org.bithon.component.commons.utils.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Dynamic Setting Manager for Plugins
 *
 * @author frank.chen021@outlook.com
 */
public class DynamicConfigurationManager {
    private static final ILogAdaptor log = LoggerFactory.getLogger(DynamicConfigurationManager.class);

    private static DynamicConfigurationManager INSTANCE = null;

    private final String appName;
    private final String env;
    private final IAgentController controller;
    private final List<IConfigurationRefreshListener> listeners;
    private Long lastModifiedAt = 0L;
    private volatile boolean running = true;
    private Object locker = new Object();

    /**
     * key: configuration name in configuration storage. Has no meaning at agent side
     * val: configuration text
     */
    private final Map<String, String> configSignatures = new HashMap<>();

    private DynamicConfigurationManager(String appName, String env, IAgentController controller) {
        this.appName = appName;
        this.env = env;
        this.controller = controller;
        this.listeners = Collections.synchronizedList(new ArrayList<>());
        this.controller.attachCommands(new ConfigCommandImpl());
        this.controller.refreshListener(this::releaseLock);
    }

    public static void createInstance(String appName, String env, IAgentController controller) {
        INSTANCE = new DynamicConfigurationManager(appName, env, controller);
        INSTANCE.startPeriodicallyFetch();
    }

    public static DynamicConfigurationManager getInstance() {
        return INSTANCE;
    }

    public void addRefreshListener(IConfigurationRefreshListener listener) {
        listeners.add(listener);
    }

    private void startPeriodicallyFetch() {
        Thread thread = new Thread(() -> {
            while (running) {
                try {
                    retrieveConfigurations();
                } catch (Throwable e) {
                    log.error("Failed to fetch plugin settings", e);
                }

                try {
                    synchronized (locker) {
                        locker.wait(60 * 1000);
                    }
                } catch (InterruptedException ignored) {
                }
            }
        });
        thread.setDaemon(true);
        thread.setName("bithon-cfg-fetcher");
        thread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Stopping bithon-cfg-fetcher...");

            running = false;
            releaseLock();
        }));
    }

    private void releaseLock() {
        synchronized (locker) {
            locker.notify();
        }
    }

    private void retrieveConfigurations() throws IOException {
        log.info("Fetch configuration for {}-{}", appName, env);

        // Get configuration from remote server
        Map<String, String> configurations = controller.getAgentConfiguration(appName, env, lastModifiedAt);
        if (CollectionUtils.isEmpty(configurations)) {
            return;
        }

        Configuration config = null;
        for (Map.Entry<String, String> entry : configurations.entrySet()) {
            String name = entry.getKey();
            String text = entry.getValue();

            // Compare signature to determine if the configuration changes
            String signature = HashGenerator.sha256Hex(text);
            if (configSignatures.getOrDefault(name, "").equals(signature)) {
                continue;
            }

            log.info("Refresh configuration [{}]", name);
            configSignatures.put(name, signature);

            try (InputStream is = new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8))) {
                Configuration cfg = Configuration.from(".json", is);

                if (config == null) {
                    config = cfg;
                } else {
                    config.merge(cfg);
                }
            }
        }
        if (config == null) {
            return;
        }

        // Update saved configuration
        // TODO: incremental configuration deletion is not supported because of complexity.
        // if the incremental configuration overwrites the global configuration, we need to restore or we can't delete the configuration directly
        // One solution is that the 'refresh' method below return the action on each returned key, ADD/REPLACE,
        // and then we keep the changed keys and corresponding actions for rollback.
        // Since it's not a must-have feature at this stage, leave it to future when we really needs it.
        Set<String> changedKeys = ConfigurationManager.getInstance().refresh(config);
        if (changedKeys.isEmpty()) {
            return;
        }

        //
        // Notify listeners about changes
        // Copy a new one to iterate to avoid concurrent problem
        List<IConfigurationRefreshListener> listeners = new ArrayList<>(this.listeners);
        for (IConfigurationRefreshListener listener : listeners) {
            try {
                listener.onRefresh(changedKeys);
            } catch (Exception e) {
                log.warn("Exception when refresh setting", e);
            }
        }

        this.lastModifiedAt = System.currentTimeMillis();
    }
}