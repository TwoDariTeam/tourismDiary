package com.team.twodari.common.security;

import com.team.twodari.common.jwt.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
    //여기서 필터 설정 잡고 JWT 필터로 넘겨서 통합해보자.
    //여기 설정을 다시 잡고 넘어가자 11-25
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http    .csrf(csrf -> csrf.disable())
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**").permitAll() // 모든 경로에 대한 접근을 허용
                );
                return http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
