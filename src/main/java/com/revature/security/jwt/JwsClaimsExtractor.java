package com.revature.security.jwt;

import com.revature.exception.ExpiredJwtTokenException;
import com.revature.exception.MalformedJwtTokenException;
import com.revature.model.type.ErrorResponseStatusType;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Objects;

@Slf4j
@Component
public class JwsClaimsExtractor {

    private Key privateSigningKey;

    private final JwtProperties jwtProperties;

    @Autowired
    public JwsClaimsExtractor(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public Jws<Claims> parseClaims(String rawToken) {
        log.debug("Parsing claims...");
        Key key = getPrivateSigningKey();
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        try {
            return jwtParser.parseClaimsJws(rawToken);
        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            throw new MalformedJwtTokenException(ErrorResponseStatusType.MALFORMED_JWT_EXCEPTION);
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            throw new ExpiredJwtTokenException(ErrorResponseStatusType.EXPIRED_JWT_EXCEPTION);
        }
    }

    private Key getPrivateSigningKey() {
        if (Objects.isNull(privateSigningKey)) {
            privateSigningKey = new SecretKeySpec(jwtProperties.getJwtSigningKey().getBytes(), SignatureAlgorithm.HS512.getJcaName());
        }
        return privateSigningKey;
    }
}
