package com.sbss.bithon.agent.core.metric.domain.web;

import com.sbss.bithon.agent.core.utils.filter.StringContainerMatcher;

import java.util.Collections;
import java.util.List;

/**
 * TODO: move out of this package
 * @author frankchen
 */
public class UserAgentFilter {

    private List<StringContainerMatcher> matchers = Collections.emptyList();

    public boolean isFiltered(String userAgent) {
        if (userAgent == null) {
            return false;
        }

        for (StringContainerMatcher matcher : matchers) {
            if (matcher.matches(userAgent)) {
                return true;
            }
        }
        return false;
    }
}