package com.revature.security.authorization;

import com.revature.security.AuthenticationDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

@Getter
@Setter
public class TrustedJwtAuthenticationToken extends AbstractAuthenticationToken {

    private Long principal;
    private Object credentials;

    public TrustedJwtAuthenticationToken(AuthenticationDetails authenticationDetails) {
        super(null);
        this.principal = authenticationDetails.getUserId();
        this.credentials = authenticationDetails.getJwtType();
        this.setAuthenticated(Boolean.TRUE);
    }

    @Override
    public Long getPrincipal() {
        return principal;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }
}
