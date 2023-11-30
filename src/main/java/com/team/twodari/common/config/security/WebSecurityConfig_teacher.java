//package com.team.twodari.common.config.security;
//
//import com.team.twodari.common.security.jwt.JwtAccessDeniedHandler;
//import com.team.twodari.common.security.jwt.JwtAuthenticationEntryPoint;
//import com.team.twodari.common.security.jwt.TokenProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.CorsFilter;
//
//
////spring.io 보고 만들었음
//@Configuration
//@EnableWebSecurity  //웹보안을 사용하겠다
//public class WebSecurityConfig {
//    //Autowired 가 더 편함 ==> 생성자 통해서 객체 저장
//    private final TokenProvider tokenProvider;
//    private final CorsFilter corsFilter;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//
//    //생성자를 이용해서 객체를 전달한다
//    public WebSecurityConfig(
//            TokenProvider tokenProvider,
//            CorsFilter corsFilter,
//            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
//            JwtAccessDeniedHandler jwtAccessDeniedHandler
//    ) {
//        this.tokenProvider = tokenProvider;
//        this.corsFilter = corsFilter;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
//    }
//    /*
//    @Bean
//    public WebSecurityCustomizer configure() throws Exception{
//
//    }*/
////    @Bean
////    public PasswordEncoder passwordEncoder(){
////        //암호화 솔루션이 따로 있을 수 있음, 그때 여기를 커스터마이징 한다
////        //UserDetails의 암호화 객체에 꽂힘
////        return new BCryptPasswordEncoder();
////    }
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        //authorizeHttpRequests -- 매개변수로 람다를 전달한다
////        //csrf 공격:자바스크립트로 남의 사이트 공격하는 것
////        //post 로 데이터를 전송
////
////        //UsernamePasswordAuthenticationFilter -- 시스템이 제공하는 기본필터, 인증필터( )
////        //가기전에 corsFilter 통과해라
//////        http
//////                .csrf(csrf->csrf.disable())  //csrf 공격대비 자바스크립트로 남이사아트 공격
//////                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//////                .authorizeHttpRequests((requests) -> {
//////                    //requests-매개변수 이름은 내맘으로
//////                    //보안을 요하지 않고 들어가도 되는 url 을 전달
//////                    //permitAll - 인증을 받지 않아도 접근 가능하다
//////                    requests.requestMatchers("/", "/home", "/api/signup", "/api/login")
//////                            .permitAll()
//////                            .anyRequest().authenticated(); //나머지는 인증이 필요하다
//////                });
//////                //폼인증 안함
////
////        return http.build();
////    }
//
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        System.out.println("********************************");
//
//        http
//                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
//                .csrf(csrf -> csrf.disable())
//
//                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(exceptionHandling -> exceptionHandling
//                        .accessDeniedHandler(jwtAccessDeniedHandler)
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                )
//
//                //https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html#authorize-requests
//
//                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
//                        .requestMatchers("/",  "/user/create", "/user/login",
//                                "/favicon.ico",
//                                "/error",
//                                "/swagger-ui/**",
//                                "/swagger-resources/**",
//                                "/api-docs/**",
//                                "/images/**").permitAll()
//                        .requestMatchers("/myapp/**").hasRole("ADMIN")
//                        .requestMatchers("/api/**").hasRole("USER")
//                        .anyRequest().authenticated()
//                )
//
//                // 세션을 사용하지 않기 때문에 STATELESS로 설정
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                )
//
//                // enable h2-console
//                .headers(headers ->
//                        headers.frameOptions(options ->
//                                options.sameOrigin()
//                        )
//                )
//
//                .apply(new JwtSecurityConfig(tokenProvider));  //우리가 만든 필터를 적용하자
//        //내가 만든걸로 바꿔치기한다
//
//        return http.build();
//    }
//
//
//    //사용자 암호나 디비로부터 로그온을 하고 싶다. 별도의 클래스를 만들어야 한다
//    /*@Bean
//    public UserDetailsService userDetailsService() {
//        //UserDetailsService -> 시스템이 제공, 우리가 상속받아별도 처리를 해야 함
//        //UserDetails - 로그인시 사용하는 기본 Dto다(useranme , password, roles 있다
//        //username을 가져면 전달된 객체를 파악해서 패스워드가 1234 임을 끄집어낸다
//        //원래들어가려던 페이지로 리다이렉트 되어야 한다
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("1234")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }*/
//}
//
//
//
