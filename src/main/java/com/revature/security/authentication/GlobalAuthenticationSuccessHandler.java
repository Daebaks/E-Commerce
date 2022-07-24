package com.revature.security.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.security.AppAuthentication;
import com.revature.security.AuthenticationDetails;
import com.revature.security.jwt.JwtAccessToken;
import com.revature.security.jwt.JwtPreAuthToken;
import com.revature.security.jwt.JwtRefreshToken;
import com.revature.service.JwtTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
public class GlobalAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final ObjectMapper objectMapper;
    private final JwtTokenService jwtTokenService;

    @Autowired
    public GlobalAuthenticationSuccessHandler(ObjectMapper objectMapper, JwtTokenService jwtTokenService) {
        this.objectMapper = objectMapper;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        TrustedAuthenticationToken trustedAuthenticationToken = (TrustedAuthenticationToken) authentication;
        AuthenticationDetails authenticationDetails = (AuthenticationDetails) trustedAuthenticationToken.getDetails();

        AppAuthentication.AppAuthenticationBuilder appAuthentication = AppAuthentication.builder();
//        if (!authenticationDetails.getEmailConfirmed()) {
//            log.info("[onAuthenticationSuccess] Generating preAuth jwt for UN-confirmed user [{}]", authenticationDetails.getUserId());
//            JwtPreAuthToken preAuthAccessToken = jwtTokenService.createPreAuthToken(authenticationDetails.getUserId());
//            appAuthentication
//                    .preAuthAccessToken(preAuthAccessToken.getRawToken())
//                    .expiresIn(preAuthAccessToken.getExpiresIn());
//        }
//        if (authenticationDetails.getEmailConfirmed()) {
//            log.info("[onAuthenticationSuccess] Generating access jwt for confirmed user [{}]", authenticationDetails.getUserId());
//            JwtAccessToken accessToken = jwtTokenService.createAccessToken(authenticationDetails.getUserId());
//            appAuthentication
//                    .accessToken(accessToken.getRawToken())
//                    .expiresIn(accessToken.getExpiresIn());
//        }

        log.info("[onAuthenticationSuccess] Generating access jwt for confirmed user [{}]", authenticationDetails.getUserId());
        JwtAccessToken accessToken = jwtTokenService.createAccessToken(authenticationDetails.getUserId());
        appAuthentication
                .accessToken(accessToken.getRawToken())
                .expiresIn(accessToken.getExpiresIn());

        if (authenticationDetails.getRememberMe()) {
            log.info("[onAuthenticationSuccess] Generating refresh jwt for remembered user [{}]", authenticationDetails.getUserId());
            JwtRefreshToken refreshToken = jwtTokenService.createRefreshToken(authenticationDetails.getUserId());
            appAuthentication
                    .refreshToken(refreshToken.getRawToken())
                    .expiresIn(refreshToken.getExpiresIn());
        }


        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), appAuthentication.build());

        clearAuthenticationAttributes(request);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(Boolean.FALSE);
        if (Objects.isNull(session)) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
