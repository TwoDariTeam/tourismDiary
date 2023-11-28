package com.team.twodari.common.config.security;

import com.team.twodari.common.constant.UserRoleConfig;
import com.team.twodari.common.security.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig {
    //여기서 필터 설정 잡고 JWT 필터로 넘겨서 통합해보자.
    //여기 설정을 다시 잡고 넘어가자 11-25
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain se(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(h -> h.disable()))
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/h2-console/**", "/", "/user/login", "/user/create", "/compareId/**","/console-path/**").permitAll() // 모든 경로에 대한 접근을 허용
                                .requestMatchers("/admin").hasRole(UserRoleConfig.UserRole.ADMIN.toString())

                )
                .logout(c -> c.deleteCookies().logoutUrl("/user/logout").logoutSuccessUrl("/"))
                .formLogin(c -> c.disable());


        return http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().
//                requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
//                .requestMatchers(new AntPathRequestMatcher( "/favicon.ico"))
//                .requestMatchers(new AntPathRequestMatcher( "/css/**"))
//                .requestMatchers(new AntPathRequestMatcher( "/js/**"))
//                .requestMatchers(new AntPathRequestMatcher( "/img/**"))
//                .requestMatchers(new AntPathRequestMatcher( "/lib/**"));
//    }
}
