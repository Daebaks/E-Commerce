package com.revature.service;


import com.revature.exception.MalformedJwtTokenException;
import com.revature.model.type.ErrorResponseStatusType;
import com.revature.model.type.JwtType;
import com.revature.security.AppAuthentication;
import com.revature.security.AuthenticationDetails;
import com.revature.security.jwt.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtTokenService {

    private final JwtTokenFactory jwtTokenFactory;

    @Autowired
    public JwtTokenService(JwtTokenFactory jwtTokenFactory) {
        this.jwtTokenFactory = jwtTokenFactory;
    }

    public JwtAccessToken createAccessToken(Long userId) {
        log.info("[createAccessToken] invoked with userId=[{}]", userId);
        return jwtTokenFactory.createAccessToken(userId);
    }

    public JwtRefreshToken createRefreshToken(Long userId) {
        log.info("[createRefreshToken] invoked with userId=[{}]", userId);
        return jwtTokenFactory.createRefreshToken(userId);
    }

    public JwtPreAuthToken createPreAuthToken(Long userId) {
        log.info("[createPreAuthToken] invoked with userId=[{}]", userId);
        return jwtTokenFactory.createPreAuthToken(userId);
    }

    public AppAuthentication refreshAuthentication(String authorizationHeader) {
        log.info("[refreshAuthentication] invoked with authorizationHeader length=[{}]", StringUtils.length(authorizationHeader));
        String rawRefreshToken = JwtHeaderTokenExtractor.extract(authorizationHeader);
        AuthenticationDetails authenticationDetails = jwtTokenFactory.verifyClaims(rawRefreshToken);
        if (ObjectUtils.notEqual(authenticationDetails.getJwtType(), JwtType.REFRESH)) {
            log.error("[refreshAuthentication] Invalid JWT token type");
            throw new MalformedJwtTokenException(ErrorResponseStatusType.MALFORMED_JWT_EXCEPTION);
        }
        return issueAuthenticationDetails(authenticationDetails.getUserId());
    }

    public AppAuthentication issueAuthenticationDetails(Long userId) {
        JwtAccessToken accessToken = jwtTokenFactory.createAccessToken(userId);
        JwtRefreshToken refreshToken = jwtTokenFactory.createRefreshToken(userId);
        return AppAuthentication.builder()
                .accessToken(accessToken.getRawToken())
                .refreshToken(refreshToken.getRawToken())
                .expiresIn(accessToken.getExpiresIn())
                .build();
    }
}
