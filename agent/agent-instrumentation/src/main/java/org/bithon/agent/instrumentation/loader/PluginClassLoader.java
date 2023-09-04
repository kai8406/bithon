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

package org.bithon.agent.instrumentation.loader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;

/**
 * @author frank.chen021@outlook.com
 * @date 28/12/21 10:31 AM
 */
public class PluginClassLoader extends ClassLoader {
    private final JarClassLoader pluginClassLoader;

    private final ClassLoader appClassLoader;

    public PluginClassLoader(JarClassLoader pluginClassLoader, ClassLoader appClassLoader) {
        // NOTE: parent is assigned to parent class loader
        // This is the key to implement agent lib isolation from app libs
        super(null);

        this.pluginClassLoader = pluginClassLoader;
        this.appClassLoader = appClassLoader;
    }

    /**
     * For classes under org.bithon.agent.plugin, we need to make sure the classes are loaded in this class loader,
     * instead of the parent class loader.
     * <p>
     * This makes sure that this class loader can find those classes referenced in the plugin.
     *
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (name.startsWith("org.bithon.agent.plugin.")) {
            try {
                byte[] classBytes = pluginClassLoader.getClassByteCode(name);
                if (classBytes != null) {
                    // define the package object for customer-loaded classes,
                    // so that getPackage could work
                    int lastIndex = name.lastIndexOf(".");
                    if (lastIndex > 0) {
                        String pkg = name.substring(0, lastIndex);
                        if (getPackage(pkg) == null) {
                            definePackage(pkg, null, null, null, null, null, null, null);
                        }
                    }
                    return defineClass(name, classBytes, 0, classBytes.length);
                }
            } catch (IOException ignored) {
            }
        }

        // Find class in parent, they might be classes in the agent library
        try {
            return pluginClassLoader.loadClass(name);
        } catch (ClassNotFoundException ignored) {
        }

        // Find in application class loader if it's referenced by the class in the plugin
        try {
            return appClassLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException(String.format(Locale.ENGLISH,
                                                           "%s not found in parent [%s] and agent plugins.",
                                                           name,
                                                           appClassLoader.getClass().getName()));
        }
    }

    @Override
    public URL getResource(String name) {
        // delegate to parent to get resource
        URL url = this.pluginClassLoader.getResource(name);
        if (url != null) {
            return url;
        }
        return appClassLoader.getResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return appClassLoader.getResources(name);
    }
}
