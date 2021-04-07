package com.sbss.bithon.agent.boot.aop;


import com.sbss.bithon.agent.boot.loader.AgentDependencyManager;
import shaded.net.bytebuddy.implementation.bind.annotation.AllArguments;
import shaded.net.bytebuddy.implementation.bind.annotation.Origin;
import shaded.net.bytebuddy.implementation.bind.annotation.RuntimeType;
import shaded.net.bytebuddy.implementation.bind.annotation.This;
import shaded.net.bytebuddy.pool.TypePool;

import java.lang.reflect.Constructor;
import java.util.Map;


/**
 * @author frankchen
 * @date 2021-02-18 18:03
 */
public class BootstrapConstructorAop {
    /**
     * assigned by {@link com.sbss.bithon.agent.core.plugin.loader.BootstrapInterceptorInstaller#generateAopClass(Map, TypePool, String, String, com.sbss.bithon.agent.core.plugin.descriptor.MethodPointCutDescriptor)}
     */
    private static String INTERCEPTOR_CLASS_NAME;

    private static AbstractInterceptor INTERCEPTOR;
    private static IAopLogger log;

    @RuntimeType
    public static void onConstruct(@Origin Class<?> targetClass,
                                   @Origin Constructor<?> constructor,
                                   @This Object targetObject,
                                   @AllArguments Object[] args) {
        try {
            AbstractInterceptor interceptor = ensureInterceptor();
            if (interceptor == null) {
                return;
            }
            interceptor.onConstruct(new AopContext(targetClass, constructor, targetObject, args));
        } catch (Throwable e) {
            if (log != null) {
                log.error(String.format("Failed to invoke onConstruct interceptor[%s]",
                                        INTERCEPTOR_CLASS_NAME),
                          e);
            } else {
                e.printStackTrace();
            }
        }
    }

    private static AbstractInterceptor ensureInterceptor() {
        if (INTERCEPTOR != null) {
            return INTERCEPTOR;
        }

        log = BootstrapHelper.createAopLogger(BootstrapMethodAop.class);

        try {
            // load class out of sync to eliminate potential dead lock
            Class<?> interceptorClass = Class.forName(INTERCEPTOR_CLASS_NAME,
                                                      true,
                                                      AgentDependencyManager.getClassLoader());
            synchronized (INTERCEPTOR_CLASS_NAME) {
                //double check
                if (INTERCEPTOR != null) {
                    return INTERCEPTOR;
                }

                INTERCEPTOR = (AbstractInterceptor) interceptorClass.newInstance();
            }
            INTERCEPTOR.initialize();
        } catch (Exception e) {
            log.error(String.format("Failed to instantiate interceptor [%s]", INTERCEPTOR_CLASS_NAME), e);
        }
        return INTERCEPTOR;
    }
}