package com.revature.security.authentication.login;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exception.ApplicationAuthenticationException;
import com.revature.exception.BadCredentialsException;
import com.revature.model.dto.UserLoginRequest;
import com.revature.model.type.ErrorResponseStatusType;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class LoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Builder
    public LoginAuthenticationProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper, AuthenticationSuccessHandler authenticationSuccessHandler,
                                               AuthenticationFailureHandler authenticationFailureHandler) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        log.info("[attemptAuthentication] Attempting to authenticate in login processing filter");
        UserLoginRequest userLoginRequest = objectMapper.readValue(request.getReader(), UserLoginRequest.class);
        if (StringUtils.isBlank(userLoginRequest.getUsername()) || StringUtils.isBlank(userLoginRequest.getPassword())) {
            log.error("[attemptAuthentication] Email and password cannot be blank");
            throw new BadCredentialsException(ErrorResponseStatusType.INVALID_CREDENTIALS_EXCEPTION);
        }
        if (ObjectUtils.isEmpty(userLoginRequest.getRememberMe())) {
            log.error("[attemptAuthentication] RememberMe flag cannot be null");
            throw new ApplicationAuthenticationException(ErrorResponseStatusType.REMEMBER_ME_EXCEPTION);
        }
        LoginAuthenticationToken authToken = new LoginAuthenticationToken(userLoginRequest);
        return this.getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        log.info("Login authentication successful");
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        log.warn("Login authentication failed");
        SecurityContextHolder.clearContext();
        authenticationFailureHandler.onAuthenticationFailure(request, response, e);
    }
}
