package com.revature.security.authentication.login;

import com.revature.exception.BadCredentialsException;
import com.revature.model.dto.UserInfoDto;
import com.revature.model.type.ErrorResponseStatusType;
import com.revature.security.AuthenticationDetails;
import com.revature.security.authentication.TrustedAuthenticationToken;
import com.revature.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("[LoginAuthenticationProvider.authenticate] Attempting to authenticate user");
        LoginAuthenticationToken loginAuthenticationToken = (LoginAuthenticationToken) authentication;
        String username = loginAuthenticationToken.getPrincipal();
        String password = loginAuthenticationToken.getCredentials();
        Boolean rememberMe = (Boolean) loginAuthenticationToken.getDetails();
        UserInfoDto userInfo = userService.getUserByUsername(username);

        if (ObjectUtils.isEmpty(userInfo)) {
            log.error("[LoginAuthenticationProvider.authenticate] Invalid email. Unauthorized");
            throw new BadCredentialsException(ErrorResponseStatusType.INVALID_CREDENTIALS_EXCEPTION);
        }
        if (!passwordEncoder.matches(password, userInfo.getPassword())) {
            log.error("[LoginAuthenticationProvider.authenticate] Invalid password. Unauthorized");
            throw new BadCredentialsException(ErrorResponseStatusType.INVALID_CREDENTIALS_EXCEPTION);
        }
        AuthenticationDetails authenticationDetails = AuthenticationDetails.builder()
                .userId(userInfo.getId())
                .rememberMe(rememberMe)
                .build();
        TrustedAuthenticationToken trustedAuthenticationToken = new TrustedAuthenticationToken(userInfo.getUsername(), userInfo.getPassword());
        trustedAuthenticationToken.setDetails(authenticationDetails);
        return trustedAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (LoginAuthenticationToken.class.isAssignableFrom(authentication));
    }
}