package com.revature.security.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class TrustedAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String principal;
    private String credentials;

    public TrustedAuthenticationToken(String principal, String credentials) {
        super(principal, credentials);
        this.principal = principal;
        this.credentials = credentials;
    }

    @Override
    public String getPrincipal() {
        return principal;
    }

    @Override
    public String getCredentials() {
        return credentials;
    }

}
