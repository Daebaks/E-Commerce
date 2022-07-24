package com.revature.security.authentication.signup;

import com.revature.model.dto.UserInfoDto;
import com.revature.security.AuthenticationDetails;
import com.revature.security.authentication.TrustedAuthenticationToken;
import com.revature.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SignUpAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;

    @Autowired
    public SignUpAuthenticationProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("[SignUpAuthenticationProvider.authenticate] Attempting to authenticate user");
        SingUpAuthenticationToken singUpAuthenticationToken = (SingUpAuthenticationToken) authentication;
        UserInfoDto userInfo = userService.createUser(singUpAuthenticationToken.getUserSignUpRequest());

        AuthenticationDetails authenticationDetails = AuthenticationDetails.builder()
                .userId(userInfo.getId())
                .build();
        TrustedAuthenticationToken trustedAuthenticationToken = new TrustedAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
        trustedAuthenticationToken.setDetails(authenticationDetails);
        return trustedAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SingUpAuthenticationToken.class.isAssignableFrom(authentication));
    }
}