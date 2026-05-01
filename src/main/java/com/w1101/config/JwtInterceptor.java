package com.w1101.config;

import com.w1101.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return true;
        }

        String token = authHeader.substring(7);

        if (JwtUtil.validateToken(token)) {
            Claims claims = JwtUtil.parseToken(token);
            if (claims != null) {
                Object userId = claims.get("userId");
                if (userId instanceof Integer) {
                    request.setAttribute("userId", ((Integer) userId).intValue());
                } else if (userId instanceof Long) {
                    request.setAttribute("userId", ((Long) userId).intValue());
                }
                request.setAttribute("username", claims.getSubject());
            }
        }

        return true;
    }
}
