package com.example.restapi.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {
    /**
     * 토큰을 생성하고 검증하는 컴포넌트 JwtTokenProvider 를 실질적으로 이용하는 Filter 클래스.
     * 필터에서 검증이 끝난 JWT 토큰에서 가져온 유저정보를 UsernamePasswordAuthenticationFilter 로 전달한다.
     *
     */

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 헤더에서 가져온 JWT 토큰
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);

        // 토큰 유효성 검사
        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 유효한 토큰일 경우, 토큰에서 유저 정보 추출
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContext 에 Authentication 객체 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
