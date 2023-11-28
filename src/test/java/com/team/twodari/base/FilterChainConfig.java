package com.team.twodari.base;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@TestConfiguration
class FilterChainConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http    .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(p -> p.anyRequest().permitAll())
                .build();
    }
}
