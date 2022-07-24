package com.revature.security.authentication.login;

import com.revature.model.dto.UserLoginRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Setter
public class LoginAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String username;
    private String password;
    private Object details;

    public LoginAuthenticationToken(UserLoginRequest userLoginRequest) {
        super(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        this.username = userLoginRequest.getUsername();
        this.password = userLoginRequest.getPassword();
        this.details = userLoginRequest.getRememberMe();
    }

    @Override
    public String getCredentials() {
        return password;
    }

    @Override
    public String getPrincipal() {
        return username;
    }

    @Override
    public Object getDetails() {
        return details;
    }
}
