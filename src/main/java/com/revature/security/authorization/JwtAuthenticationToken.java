package com.revature.security.authorization;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String credentials;

    public JwtAuthenticationToken(String credentials) {
        super(null);
        this.credentials = credentials;
        this.setAuthenticated(Boolean.FALSE);
    }

    @Override
    public String getPrincipal() {
        return null;
    }

    @Override
    public String getCredentials() {
        return credentials;
    }
}
