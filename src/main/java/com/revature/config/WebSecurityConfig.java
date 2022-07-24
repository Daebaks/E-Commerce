package com.revature.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.security.authentication.GlobalAuthenticationFailureHandler;
import com.revature.security.authentication.GlobalAuthenticationSuccessHandler;
import com.revature.security.authentication.RestAuthenticationEntryPoint;
import com.revature.security.authentication.login.LoginAuthenticationProcessingFilter;
import com.revature.security.authentication.login.LoginAuthenticationProvider;
import com.revature.security.authentication.signup.SignUpAuthenticationProcessingFilter;
import com.revature.security.authentication.signup.SignUpAuthenticationProvider;
import com.revature.security.authorization.JwtAuthenticationProcessingFilter;
import com.revature.security.authorization.JwtAuthenticationProvider;
import com.revature.security.authorization.JwtAuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_ENTRY_POINT = "/auth/login";
    private static final String SIGN_UP_ENTRY_POINT = "/auth/sign-up";

    @Autowired
    @Qualifier("authWhiteList")
    private String[] authWhiteList;
    @Autowired
    @Qualifier("jwtRequestMatcher")
    private RequestMatcher requestMatcher;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;
    @Autowired
    private SignUpAuthenticationProvider signUpAuthenticationProvider;
    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;
    @Autowired
    private GlobalAuthenticationSuccessHandler globalAuthenticationSuccessHandler;
    @Autowired
    private GlobalAuthenticationFailureHandler globalAuthenticationFailureHandler;
    @Autowired
    private JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        log.debug("Enabling Login Authentication Provider");
        auth.authenticationProvider(loginAuthenticationProvider);

        log.debug("Enabling SignUp Authentication Provider");
        auth.authenticationProvider(signUpAuthenticationProvider);

        log.debug("Enabling JWT Authentication Provider");
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(authWhiteList).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(getSignUpAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(getLoginAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(getJwtAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private LoginAuthenticationProcessingFilter getLoginAuthenticationProcessingFilter() {
        LoginAuthenticationProcessingFilter loginAuthenticationProcessingFilter = LoginAuthenticationProcessingFilter.builder()
                .defaultFilterProcessesUrl(LOGIN_ENTRY_POINT)
                .objectMapper(objectMapper)
                .authenticationSuccessHandler(globalAuthenticationSuccessHandler)
                .authenticationFailureHandler(globalAuthenticationFailureHandler)
                .build();
        loginAuthenticationProcessingFilter.setAuthenticationManager(this.authenticationManager);
        return loginAuthenticationProcessingFilter;
    }

    private SignUpAuthenticationProcessingFilter getSignUpAuthenticationProcessingFilter() {
        SignUpAuthenticationProcessingFilter signUpAuthenticationProcessingFilter = SignUpAuthenticationProcessingFilter.builder()
                .defaultFilterProcessesUrl(SIGN_UP_ENTRY_POINT)
                .objectMapper(objectMapper)
                .authenticationSuccessHandler(globalAuthenticationSuccessHandler)
                .authenticationFailureHandler(globalAuthenticationFailureHandler)
                .build();
        signUpAuthenticationProcessingFilter.setAuthenticationManager(this.authenticationManager);
        return signUpAuthenticationProcessingFilter;
    }

    private JwtAuthenticationProcessingFilter getJwtAuthenticationProcessingFilter() {
        JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter = JwtAuthenticationProcessingFilter.builder()
                .requestMatcher(requestMatcher)
                .authenticationSuccessHandler(jwtAuthenticationSuccessHandler)
                .handlerExceptionResolver(handlerExceptionResolver)
                .build();
        jwtAuthenticationProcessingFilter.setAuthenticationManager(this.authenticationManager);
        return jwtAuthenticationProcessingFilter;
    }
}
