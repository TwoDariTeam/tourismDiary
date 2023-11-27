package com.team.twodari.common.config.security;

import com.team.twodari.common.security.jwt.JwtFilter;
import com.team.twodari.common.security.jwt.TokenProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    //설명서보니 cors().disable->corsFilter이름의 Bean제공되면 넘어갈듯 csrf() 얘도 마찬가지.
    //logout EnableWebSecurity 사용할 때 자동으로 적용. 기본값은 /logout
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(PathRequest.toH2Console()).permitAll().anyRequest().authenticated())
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

    }

}

