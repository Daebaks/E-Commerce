package com.revature.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String jwtIssuer;

    private String jwtSigningKey;

    private Long accessTokenExpirationTime;

    private Long refreshTokenExpirationTime;

    private Long preAuthTokenExpirationTime;
}
