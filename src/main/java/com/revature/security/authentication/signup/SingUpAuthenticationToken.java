package com.revature.security.authentication.signup;

import com.revature.model.dto.UserSignUpRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class SingUpAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private UserSignUpRequest userSignUpRequest;

    public SingUpAuthenticationToken(UserSignUpRequest userSignUpRequest) {
        super(userSignUpRequest.getUsername(), userSignUpRequest.getPassword());
        this.userSignUpRequest = userSignUpRequest;
    }

    @Override
    public String getPrincipal() {
        return userSignUpRequest.getUsername();
    }

    @Override
    public String getCredentials() {
        return userSignUpRequest.getPassword();
    }
}
