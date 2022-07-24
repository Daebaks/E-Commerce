package com.revature.security.authentication.signup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.dto.UserSignUpRequest;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
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
public class SignUpAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private final ObjectMapper objectMapper;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Builder
    public SignUpAuthenticationProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper, AuthenticationSuccessHandler authenticationSuccessHandler,
                                                AuthenticationFailureHandler authenticationFailureHandler) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        log.info("[attemptAuthentication] Attempting to authenticate in signUp processing filter");
        UserSignUpRequest userSignUpRequest = objectMapper.readValue(request.getReader(), UserSignUpRequest.class);
        SingUpAuthenticationToken authToken = new SingUpAuthenticationToken(userSignUpRequest);
        return this.getAuthenticationManager().authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authentication) throws IOException, ServletException {
        log.info("SignUp authentication successful");
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) throws IOException, ServletException {
        log.warn("SignUp authentication failed");
        SecurityContextHolder.clearContext();
        authenticationFailureHandler.onAuthenticationFailure(request, response, e);
    }
}
