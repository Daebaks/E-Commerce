package com.revature.security.authorization;

import com.revature.security.AuthenticationDetails;
import com.revature.security.jwt.JwtTokenFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtTokenFactory jwtTokenFactory;

    @Autowired
    public JwtAuthenticationProvider(JwtTokenFactory jwtTokenFactory) {
        this.jwtTokenFactory = jwtTokenFactory;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("[JwtAuthenticationProvider.authenticate] Attempting to authenticate user");
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        String rawAccessToken = jwtAuthenticationToken.getCredentials();

        AuthenticationDetails authenticationDetails = jwtTokenFactory.verifyClaims(rawAccessToken);
        return new TrustedJwtAuthenticationToken(authenticationDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
