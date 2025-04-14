package com.guld.sciq.security.jwt;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    public static final String AUTHORIZATION_HEADER_NAME = "Authorization";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;

    // 실제 필터링 로직은 doFilterInternal 에 들어감
    // JWT 토큰의 인증 정보를 현재 쓰레드의 SecurityContext 에 저장하는 역할 수행
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        log.debug("JwtAuthenticationFilter 시작 - 요청 URI: {}", request.getRequestURI());

        try {
            // 1. Request Header 에서 토큰을 꺼냄
            String jwt = getJwtFromRequest(request);
            log.debug("추출된 JWT 토큰: {}", jwt);

            // 2. validateToken 으로 토큰 유효성 검사
            // 정상 토큰이면 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장
            if (StringUtils.hasText(jwt)) {
                log.debug("토큰 검증 시작");
                if (tokenProvider.validateToken(jwt)) {
                    log.debug("토큰 검증 성공");
                    Authentication authentication = tokenProvider.getAuthentication(jwt, request);
                    log.debug("생성된 Authentication: {}", authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    log.debug("토큰 검증 실패");
                }
            } else {
                log.debug("요청에 토큰이 없습니다");
            }
        } catch (Exception e){
            log.error("Could not set user authentication in security context", e);
        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER_NAME);
        log.debug("Authorization 헤더: {}", bearerToken);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TOKEN_PREFIX)) {
            String token = bearerToken.substring(7, bearerToken.length());
            log.debug("추출된 토큰: {}", token);
            return token;
        }
        return null;
    }
}
