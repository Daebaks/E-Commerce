package com.revature.security.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JwtPreAuthToken {

    private String rawToken;

    private Long expiresIn;

}
