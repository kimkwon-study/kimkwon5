package com.hanhae.hanhae99.global.filter;

import com.hanhae.hanhae99.global.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j(topic = "AuthFilter")
@RequiredArgsConstructor
@Component
public class AuthFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.startsWith("/register") || url.startsWith("/login") ||
                        url.matches("^/api/find/.*$") || url.startsWith("/api/findall")
                )
        ) {
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    responseError("{\"error\": \"올바르지 않는 토큰 입니다.\"}", response);
                }

                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                responseError("{\"error\": \"토큰이 존재하지 않습니다.\"}", response);
            }
        }


    }

    private void responseError(String errorMsg, ServletResponse response) throws IOException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        String error = errorMsg;
        writer.print(error);
        writer.flush();

        log.error(errorMsg);
    }

}
