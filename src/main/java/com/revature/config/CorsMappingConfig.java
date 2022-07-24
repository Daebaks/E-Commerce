package com.revature.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMappingConfig implements WebMvcConfigurer {

    private static final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods(allowedHttpMethods())
                .maxAge(MAX_AGE_SECS);
    }

    private String[] allowedHttpMethods() {
        return new String[]{
                HttpMethod.HEAD.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.GET.name(),
                HttpMethod.POST.name(),
                HttpMethod.PUT.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.DELETE.name()
        };
    }
}
