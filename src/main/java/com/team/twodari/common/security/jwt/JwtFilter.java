package com.team.twodari.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
    private TokenProvider tokenProvider;

    public JwtFilter(TokenProvider tokenProvider) {

        this.tokenProvider = tokenProvider;

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String jwt = resolveToken(httpServletRequest); //head에서 토큰을 추출한다
        System.out.println("토큰 유무 판정기입니다."+jwt);
        String requestURI = httpServletRequest.getRequestURI(); //현재 요청 url
        if (StringUtils.hasText(jwt)) {
            Map<String, String> resultMap = tokenProvider.validateToken(jwt);
            if (resultMap.get("result").equals("SUCCESS")) {
                Authentication authentication = tokenProvider.getAuthentication(jwt);
//             getCookie(httpServletRequest);

                SecurityContextHolder.getContext().setAuthentication(authentication);
                logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
            } else {
                servletRequest.setAttribute("tokenexception", resultMap);
                logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);

            }

        } else {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("result", "fail");
            map.put("msg", "인증정보가 없습니다");
            servletRequest.setAttribute("tokenexception", map);
        }


        filterChain.doFilter(servletRequest, servletResponse);

    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }


}
