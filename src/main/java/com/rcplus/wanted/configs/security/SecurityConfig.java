package com.rcplus.wanted.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth->{ auth.anyRequest().permitAll(); })
            .csrf(csrf->csrf.disable())
            .headers(headers->headers.frameOptions(frame->{ frame.sameOrigin(); }))
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .httpBasic(basic->basic.disable())
            .build();
    }
    
}
