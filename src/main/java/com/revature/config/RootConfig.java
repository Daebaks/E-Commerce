package com.revature.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.revature.security.jwt.JwtProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class RootConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new Jackson2ObjectMapperBuilder().propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE).build();
    }

    @Bean
    public TextEncryptor textEncryptor(@Value("${encryptor.password}") String password, @Value("${encryptor.salt}") String salt) {
        return Encryptors.text(password, salt);
    }
}
