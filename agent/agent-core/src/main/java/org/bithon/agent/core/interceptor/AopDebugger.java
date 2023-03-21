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

package org.bithon.agent.core.interceptor;


import org.bithon.agent.bootstrap.utils.AgentDirectory;
import org.bithon.agent.core.config.ConfigurationManager;
import org.bithon.shaded.net.bytebuddy.description.type.TypeDescription;
import org.bithon.shaded.net.bytebuddy.dynamic.DynamicType;
import org.bithon.shaded.net.bytebuddy.utility.JavaModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Set;

import static java.io.File.separator;

/**
 * @author frankchen
 */
public class AopDebugger extends AopTransformationListener {
    public static final File CLASS_FILE_DIR;

    /**
     * corresponding to <b>bithon.aop.debug</b> configuration item
     */
    public static final boolean IS_DEBUG_ENABLED;

    static {
        IS_DEBUG_ENABLED = ConfigurationManager.getInstance().getConfig(AopConfig.class).isDebug();

        if (!IS_DEBUG_ENABLED) {
            CLASS_FILE_DIR = null;
        } else {
            String appName = ConfigurationManager.getInstance().getConfig("bithon.application.name", String.class);
            String env = ConfigurationManager.getInstance().getConfig("bithon.application.env", String.class);

            CLASS_FILE_DIR = AgentDirectory.getSubDirectory(AgentDirectory.TMP_DIR
                                                            + separator
                                                            + appName + "-" + env
                                                            + separator
                                                            + "classes");

            // clean up directories before startup
            // this is convenient for debugging
            try {
                Files.walk(CLASS_FILE_DIR.toPath())
                     .sorted(Comparator.reverseOrder())
                     .map(Path::toFile)
                     .forEach(File::delete);
            } catch (IOException ignored) {
            }

            try {
                if (!CLASS_FILE_DIR.exists()) {
                    CLASS_FILE_DIR.mkdirs();
                }
            } catch (Exception e) {
                log.error("log error", e);
            }
        }
    }

    private final Set<String> types;

    public AopDebugger(Set<String> types) {
        this.types = types;
    }

    public static void saveClassToFile(DynamicType dynamicType) {
        if (!IS_DEBUG_ENABLED) {
            return;
        }
        try {
            log.info("Saving [{}] to [{}]...", dynamicType.getTypeDescription().getTypeName(), CLASS_FILE_DIR);
            dynamicType.saveIn(CLASS_FILE_DIR);
        } catch (Throwable e) {
            log.warn("Failed to save class {} to file." + dynamicType.getTypeDescription().getActualName(), e);
        }
    }

    @Override
    public void onTransformation(TypeDescription typeDescription,
                                 ClassLoader classLoader,
                                 JavaModule javaModule,
                                 boolean loaded, DynamicType dynamicType) {
        if (IS_DEBUG_ENABLED && this.types.contains(typeDescription.getTypeName())) {
            log.info("{} Transformed", typeDescription.getTypeName());
            saveClassToFile(dynamicType);
        }
    }
}