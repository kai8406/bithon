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

package org.bithon.agent.plugin.spring.bean.installer;

import org.bithon.agent.core.aop.BeanMethodAopInstaller;
import org.bithon.agent.core.aop.InstrumentationHelper;
import org.bithon.agent.core.plugin.PluginConfigurationManager;
import org.bithon.agent.core.utils.bytecode.ByteCodeUtils;
import org.bithon.agent.plugin.spring.bean.SpringBeanPlugin;
import shaded.net.bytebuddy.dynamic.loading.ClassInjector;

import java.util.HashMap;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/7/10 13:05
 */
public class BeanMethodAopInstallerHelper {

    static BeanMethodAopInstaller.BeanTransformationConfig transformationConfig;

    public static void initialize() {
        transformationConfig = PluginConfigurationManager.load(SpringBeanPlugin.class)
                                                         .getConfig("agent.plugin.spring.bean", BeanMethodAopInstaller.BeanTransformationConfig.class);

        //
        // inject interceptor classes into bootstrap class loader to ensure this interceptor classes could be found by Adviced code which would be loaded by application class loader
        // because for any class loader, it would back to bootstrap class loader to find class first
        //
        ClassInjector.UsingUnsafe.Factory.resolve(InstrumentationHelper.getInstance()).make(null, null).injectRaw(new HashMap<String, byte[]>() {
            {
                put(IBeanMethodInterceptor.class.getName(),
                    ByteCodeUtils.getClassByteCode(IBeanMethodInterceptor.class.getName(), IBeanMethodInterceptor.class.getClassLoader()));
                put(BeanMethodInterceptorFactory.class.getName(),
                    ByteCodeUtils.getClassByteCode(BeanMethodInterceptorFactory.class.getName(), BeanMethodInterceptorFactory.class.getClassLoader()));
            }
        });
    }

    public static void install(Class<?> targetClass) {
        BeanMethodAopInstaller.install(targetClass, BeanMethodAop.class, transformationConfig);
    }
}
