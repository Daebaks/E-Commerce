package com.revature.security.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class GlobalAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final HandlerExceptionResolver handlerExceptionResolver;

    @Autowired
    public GlobalAuthenticationFailureHandler(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.info("[onAuthenticationFailure] Failed to authenticate: {}", e.getMessage());
        handlerExceptionResolver.resolveException(request, response, null, e);
    }
}