package com.nts.ntsboard.common.auth;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public class AuthorizationFilter implements Filter {
    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String authHeader = httpRequest.getHeader(JwtUtil.AUTHORIZATION_HEADER);

        if (authHeader == null || !authHeader.startsWith(JwtUtil.BEARER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = authHeader.substring(JwtUtil.BEARER_PREFIX.length());
        if (!jwtUtil.validateToken(token)) {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("invalid token");
            return;
        }

        Long userId = jwtUtil.getUserId(token);
        httpRequest.setAttribute("userId", userId);
        chain.doFilter(request, response);
    }
}
