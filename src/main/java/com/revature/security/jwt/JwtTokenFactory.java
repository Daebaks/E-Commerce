package com.revature.security.jwt;

import com.revature.exception.MalformedJwtTokenException;
import com.revature.model.type.ErrorResponseStatusType;
import com.revature.model.type.JwtType;
import com.revature.security.AuthenticationDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenFactory {

    private Key privateSigningKey;
    private final JwtProperties jwtProperties;
    private final JwsClaimsExtractor jwsClaimsExtractor;

    @Autowired
    public JwtTokenFactory(JwtProperties jwtProperties, JwsClaimsExtractor jwsClaimsExtractor) {
        this.jwtProperties = jwtProperties;
        this.jwsClaimsExtractor = jwsClaimsExtractor;
    }

    public JwtAccessToken createAccessToken(Long claim) {
        Claims claims = Jwts.claims()
                .setId(String.valueOf(claim))
                .setSubject(JwtType.ACCESS.name());

        Instant issuedAt = Instant.now();
        Long expirationTimeInterval = jwtProperties.getAccessTokenExpirationTime();
        Instant expiration = issuedAt.plus(expirationTimeInterval, ChronoUnit.SECONDS);

        Key privateSigningKey = getPrivateSigningKey();
        String rawToken = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getJwtIssuer())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS512, privateSigningKey)
                .compact();
        return JwtAccessToken.builder()
                .rawToken(rawToken)
                .expiresIn(expirationTimeInterval)
                .build();
    }

    public JwtRefreshToken createRefreshToken(Long claim) {
        Claims claims = Jwts.claims()
                .setId(String.valueOf(claim))
                .setSubject(JwtType.REFRESH.name());

        Instant issuedAt = Instant.now();
        Long expirationTimeInterval = jwtProperties.getRefreshTokenExpirationTime();
        Instant expiration = issuedAt.plus(expirationTimeInterval, ChronoUnit.SECONDS);

        Key privateSigningKey = getPrivateSigningKey();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getJwtIssuer())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS512, privateSigningKey)
                .compact();
        return JwtRefreshToken.builder()
                .rawToken(token)
                .expiresIn(expirationTimeInterval)
                .build();
    }

    public JwtPreAuthToken createPreAuthToken(Long claim) {
        Claims claims = Jwts.claims()
                .setId(String.valueOf(claim))
                .setSubject(JwtType.PRE_AUTH.name());

        Instant issuedAt = Instant.now();
        Long expirationTimeInterval = jwtProperties.getPreAuthTokenExpirationTime();
        Instant expiration = issuedAt.plus(expirationTimeInterval, ChronoUnit.SECONDS);

        Key privateSigningKey = getPrivateSigningKey();
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtProperties.getJwtIssuer())
                .setIssuedAt(Date.from(issuedAt))
                .setExpiration(Date.from(expiration))
                .signWith(SignatureAlgorithm.HS512, privateSigningKey)
                .compact();
        return JwtPreAuthToken.builder()
                .rawToken(token)
                .expiresIn(expirationTimeInterval)
                .build();
    }

    public AuthenticationDetails verifyClaims(String rawJwt) {
        Jws<Claims> jwsClaims = jwsClaimsExtractor.parseClaims(rawJwt);
        Claims claimsBody = jwsClaims.getBody();

        String jwtType = claimsBody.getSubject();
        if (ObjectUtils.isEmpty(jwtType)) {
            log.error("[verifyClaims] Invalid JWT token type");
            throw new MalformedJwtTokenException(ErrorResponseStatusType.MALFORMED_JWT_EXCEPTION);
        }
        String jwtIssuer = claimsBody.getIssuer();
        if (ObjectUtils.notEqual(jwtIssuer, jwtProperties.getJwtIssuer())) {
            log.error("[verifyClaims] Invalid JWT issuer, expected: {}, got: {}", jwtProperties.getJwtIssuer(), jwtIssuer);
            throw new MalformedJwtTokenException(ErrorResponseStatusType.MALFORMED_JWT_EXCEPTION);
        }
        String rawUserId = claimsBody.getId();
        return AuthenticationDetails.builder()
                .userId(Long.valueOf(rawUserId))
                .jwtType(JwtType.valueOf(jwtType))
                .build();
    }

    private Key getPrivateSigningKey() {
        if (ObjectUtils.isEmpty(privateSigningKey)) {
            privateSigningKey = new SecretKeySpec(jwtProperties.getJwtSigningKey().getBytes(), SignatureAlgorithm.HS512.getJcaName());
        }
        return privateSigningKey;
    }
}
