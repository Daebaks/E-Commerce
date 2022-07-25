package com.revature.controller;


import com.revature.model.dto.UserLoginRequest;
import com.revature.model.dto.UserSignUpRequest;
import com.revature.security.AppAuthentication;
import com.revature.service.JwtTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppAuthentication login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return null;
    }
    
    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppAuthentication signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest) {
        return null;
    }
    @Operation(summary = "Retrieve new Authentication Token", security = { @SecurityRequirement(name = "bearer-token") })
    @PostMapping("/refresh")
    public AppAuthentication refreshAuthentication(HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        return jwtTokenService.refreshAuthentication(authorizationHeader);
    }
}
