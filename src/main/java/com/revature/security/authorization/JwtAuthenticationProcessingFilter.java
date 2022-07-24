package com.revature.security.authorization;

import com.revature.security.jwt.JwtHeaderTokenExtractor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class JwtAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Builder
    public JwtAuthenticationProcessingFilter(RequestMatcher requestMatcher, AuthenticationSuccessHandler authenticationSuccessHandler,
                                             HandlerExceptionResolver handlerExceptionResolver) {
        super(requestMatcher);
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.debug("[attemptAuthentication] Attempting to authenticate in JWT processing filter");
        String accessTokenPayload = request.getHeader(HttpHeaders.AUTHORIZATION);
        String rawAccessToken = JwtHeaderTokenExtractor.extract(accessTokenPayload);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(rawAccessToken);
        return getAuthenticationManager().authenticate(jwtAuthenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        log.debug("Successful JWT Authentication");
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, chain, authentication);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        log.debug("Unsuccessful JWT Authentication: {}", e.getMessage());
        SecurityContextHolder.clearContext();
        handlerExceptionResolver.resolveException(request, response, null, e);
    }
}
