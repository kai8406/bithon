package com.sbss.bithon.agent.plugin.undertow.interceptor;

import com.sbss.bithon.agent.core.metrics.web.RequestUriFilter;
import com.sbss.bithon.agent.core.metrics.web.UserAgentFilter;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AbstractInterceptor;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AopContext;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.InterceptionDecision;
import com.sbss.bithon.agent.plugin.undertow.metric.WebRequestMetricProvider;
import io.undertow.server.HttpServerExchange;

/**
 * @author frankchen
 */
public class HttpServerExchangeDispatch extends AbstractInterceptor {

    private UserAgentFilter userAgentFilter;
    private RequestUriFilter uriFilter;

    @Override
    public boolean initialize() {
        //make sure initialized
        WebRequestMetricProvider.getInstance();

        userAgentFilter = new UserAgentFilter();
        uriFilter = new RequestUriFilter();

        return true;
    }

    @Override
    public InterceptionDecision onMethodEnter(AopContext context) {
        final HttpServerExchange exchange = context.castTargetAs();

        if (this.userAgentFilter.isFiltered(exchange.getRequestHeaders().getFirst("User-Agent"))
            || this.uriFilter.isFiltered(exchange.getRequestPath())) {
            return InterceptionDecision.SKIP_LEAVE;
        }

        final long startTime = System.nanoTime();
        exchange.addExchangeCompleteListener((listener,
                                              next) -> {
            WebRequestMetricProvider.getInstance().update(exchange, startTime);
            next.proceed();
        });
        return InterceptionDecision.CONTINUE;
    }
}
