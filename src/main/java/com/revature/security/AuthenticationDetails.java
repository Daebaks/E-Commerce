package com.revature.security;

import com.revature.model.type.JwtType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationDetails {

    private Long userId;

    private JwtType jwtType;

    private Boolean rememberMe;

}
