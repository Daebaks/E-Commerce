package com.revature.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppRoutingConfig {

    @Bean
    @Qualifier("authWhiteList")
    public String[] getAuthWhiteList() {
        return new String[]{

                //swagger
                "/ecommerce-swagger/**",
                "/swagger-resources/**",
                "/swagger-ui/**",
                "/webjars/**",

                // open api
                "/swagger-ui.html",
                "/swagger-ui.html/**",
                "/v3/api-docs/swagger-config",
                "/v3/api-docs/**",


                //web
                "/",
                "/csrf",
                "/error",
                "/actuator/**",
                "/favicon.ico",
                "/auth/login",
                "/auth/sign-up",
                "/access/refresh",
                "/access/email/confirm"
        };
    }

    @Bean
    @Qualifier("preAuthJwtPaths")
    public List<String> getPreAuthJwtPaths() {
        List<String> authList = new ArrayList<>();
        authList.add("/access/email/confirm");
        return authList;
    }
}
