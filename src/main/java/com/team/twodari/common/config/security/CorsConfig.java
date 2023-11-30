//package com.team.twodari.common.config.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
////CORS정책떄문에 진행한다
////@Configuration
//public class CorsConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOriginPattern("");
//        config.addAllowedHeader("");
//        config.addAllowedMethod("*");
//
//        source.registerCorsConfiguration("/user/**", config);
//
//        return new CorsFilter(source);
//    }
//}