package com.guld.sciq.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import com.guld.sciq.exception.auth.InvalidTokenException;
import com.guld.sciq.security.UserPrincipal;
import com.guld.sciq.security.dto.TokenDto;

@Component
public class TokenProvider {
    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);

    private static final String AUTHORITIES_KEY = "auth";
    private static final String USER_ID = "id";
    private static final String USER_EMAIL = "email";
    private static final String USER_NICKNAME = "nickname";
    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final Key key;

    // yml 에서 jwt.secret 에 저장된 난수를 불러와서 decode 상수로 사용합니다.
    public TokenProvider(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenDto.Response generateToken(Authentication authentication) {
        log.debug("토큰 생성 시작 - Authentication: {}", authentication);
        log.debug("Principal: {}", authentication.getPrincipal());
        log.debug("Authorities: {}", authentication.getAuthorities());

        // 권한들 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        log.debug("권한 문자열: {}", authorities);

        long now = (new Date()).getTime();
        log.debug("현재 시간: {}", now);

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        log.debug("Access Token 만료 시간: {}", accessTokenExpiresIn);

        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        log.debug("UserPrincipal 정보 - ID: {}, Email: {}", userPrincipal.getId(), userPrincipal.getEmail());

        Claims claims = Jwts.claims();
        log.debug("새로운 Claims 객체 생성");

        claims.put(AUTHORITIES_KEY, authorities);
        claims.put(USER_ID, userPrincipal.getId());
        claims.put(USER_EMAIL, userPrincipal.getEmail());
        claims.put(USER_NICKNAME, userPrincipal.getNickName());
        claims.setSubject(userPrincipal.getEmail());
        log.debug("Claims 설정 완료: {}", claims);

        String accessToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        log.debug("생성된 Access Token: {}", accessToken);
        log.debug("Access Token 길이: {}", accessToken.length());
        log.debug("Access Token 만료 시간: {}", accessTokenExpiresIn);

        // Refresh Token 생성
        Date refreshTokenExpiresIn = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);
        log.debug("Refresh Token 만료 시간: {}", refreshTokenExpiresIn);

        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        log.debug("생성된 Refresh Token: {}", refreshToken);
        log.debug("Refresh Token 길이: {}", refreshToken.length());
        log.debug("Refresh Token 만료 시간: {}", refreshTokenExpiresIn);

        TokenDto.Response response = TokenDto.Response.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
                .refreshToken(refreshToken)
                .build();

        log.debug("생성된 TokenDto.Response: {}", response);
        return response;
    }

    public Authentication getAuthentication(String accessToken, HttpServletRequest request) {
        log.debug("토큰 인증 시작 - Access Token: {}", accessToken);

        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
        log.debug("복호화된 Claims: {}", claims);

        if (claims.get(AUTHORITIES_KEY) == null) {
            log.error("권한 정보가 없는 토큰입니다.");
            throw new InvalidTokenException();
        }

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .toList();
        log.debug("추출된 권한: {}", authorities);

        // UserPrincipal 객체를 만들어서 Authentication 리턴
        UserPrincipal principal = new UserPrincipal(
                Long.parseLong(claims.get(USER_ID).toString()),
                claims.get(USER_EMAIL).toString(),
                claims.get(USER_NICKNAME).toString(),
                "",
                authorities
        );
        log.debug("생성된 UserPrincipal: {}", principal);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal, "", authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    public boolean validateToken(String token) {
        log.debug("토큰 검증 시작 - Token: {}", token);
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            log.debug("토큰 검증 성공");
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다. 에러: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다. 에러: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다. 에러: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다. 에러: {}", e.getMessage());
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        log.debug("토큰 파싱 시작 - Access Token: {}", accessToken);
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
            log.debug("파싱된 Claims: {}", claims);
            return claims;
        } catch (ExpiredJwtException e) {
            log.error("만료된 토큰 파싱 - Claims: {}", e.getClaims());
            return e.getClaims();
        }
    }
}
