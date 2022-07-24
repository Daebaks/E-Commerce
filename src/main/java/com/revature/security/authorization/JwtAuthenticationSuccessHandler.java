package com.revature.security.authorization;

import com.revature.exception.ForbiddenException;
import com.revature.model.type.ErrorResponseStatusType;
import com.revature.model.type.JwtType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final List<String> preAuthJwtPaths;

    @Autowired
    public JwtAuthenticationSuccessHandler(@Qualifier("preAuthJwtPaths") List<String> preAuthJwtPaths) {
        this.preAuthJwtPaths = preAuthJwtPaths;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        TrustedJwtAuthenticationToken trustedJwtAuthenticationToken = (TrustedJwtAuthenticationToken) authentication;
        JwtType jwtType = (JwtType) trustedJwtAuthenticationToken.getCredentials();
        if (Objects.equals(jwtType, JwtType.PRE_AUTH) && !preAuthJwtPaths.contains(request.getServletPath())) {
            log.error("[onAuthenticationSuccess] Authenticated, but forbidden path requested [{}]", request.getServletPath());
            throw new ForbiddenException(ErrorResponseStatusType.FORBIDDEN_EXCEPTION);
        }
    }
}
