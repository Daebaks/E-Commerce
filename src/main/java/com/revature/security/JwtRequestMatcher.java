package com.revature.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class JwtRequestMatcher implements RequestMatcher {

    private static final String JWT_ENTRY_POINT = "/**";

    private final OrRequestMatcher matchers;
    private final RequestMatcher processingMatcher;

    @Autowired
    public JwtRequestMatcher(@Qualifier("authWhiteList") String[] pathsToSkip) {
        List<RequestMatcher> requestMatchers = Arrays.stream(pathsToSkip)
                .map(AntPathRequestMatcher::new)
                .collect(toList());
        matchers = new OrRequestMatcher(requestMatchers);
        processingMatcher = new AntPathRequestMatcher(JWT_ENTRY_POINT);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if (matchers.matches(request)) {
            return Boolean.FALSE;
        }
        return processingMatcher.matches(request);
    }
}
