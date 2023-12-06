package com.team.twodari.common.security.jwt;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401에러를 발생시킨다
        Map<String, String> map = (Map<String, String>) request.getAttribute("tokenexception");
        if (map == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        setResponse(response, 1, map.get("msg"));


    }

    //별도로 만든 함수임
    private void setResponse(HttpServletResponse response, int code, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println("{ \"message\" : \"" + msg
                + "\", \"code\" : \"" + code
                + "\", \"status\" : " + "500"
                + ", \"errors\" : [ ] }");
    }

}
