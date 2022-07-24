package com.revature.controller;


import com.revature.model.dto.UserLoginRequest;
import com.revature.model.dto.UserSignUpRequest;
import com.revature.security.AppAuthentication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppAuthentication login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        return null;
    }

    @PostMapping(value = "/sign-up", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppAuthentication signUp(@RequestBody @Valid UserSignUpRequest userSignUpRequest) {
        return null;
    }
}
