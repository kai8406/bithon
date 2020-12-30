package com.sbss.bithon.agent.plugin.springweb;

import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AopContext;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AbstractInterceptor;
import com.sbss.bithon.agent.core.utils.CollectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import shaded.org.slf4j.Logger;
import shaded.org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * frank.chen021@outlook.com
 */
public class MethodMatchingInterceptor extends AbstractInterceptor {
    private static final Logger log = LoggerFactory.getLogger(MethodMatchingInterceptor.class);

    @Override
    public void onMethodLeave(AopContext aopContext) {
        Method mappingToMethod = (Method)aopContext.getArgs()[1];

        Object mapping = aopContext.getArgs()[2];
        if (!(mapping instanceof RequestMappingInfo)) {
            log.warn("spring mvc registering mapping pattern with unrecognized class");
            return;
        }

        RequestMappingInfo mappingInfo = (RequestMappingInfo) mapping;
        Set<String> patterns = mappingInfo.getPatternsCondition().getPatterns();
        if (CollectionUtils.isEmpty(patterns)) {
            return;
        }

        //TODO: keep patterns in temp storage
        // and send the patterns after detectHandlerMethods(final Object handler) has been intercepted
//        EventMessage eventMessage = new EventMessage();
//        Dispatcher eventDispatcher = Dispatchers.getOrCreate(Dispatchers.DISPATCHER_NAME_EVNETS);
//        eventDispatcher.sendMessage(eventMessage);
    }
}
