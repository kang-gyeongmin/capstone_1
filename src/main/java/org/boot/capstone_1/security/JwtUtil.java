package org.boot.capstone_1.security;

import static org.boot.capstone_1.security.JwtConstant.ACCESS_TOKEN_EXPIRE_TIME;
import static org.boot.capstone_1.security.JwtConstant.REFRESH_TOKEN_EXPIRE_TIME;

import java.util.HashSet;
import java.util.Set;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.boot.capstone_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtil {

    public final Key key;

    @Autowired
    private UserService userService;

    // application.yml 에서 secret 값 가져와서 key 에 저장
    public JwtUtil(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // User 정보를 가지고 AccessToken만 생성하는 메서드
    public String generateAccessToken(String userId) {
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();


        // 로그로 토큰을 출력
        log.info("Access Token: {}", accessToken);

        return accessToken;
    }

    // User 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public JwtDTO generateToken(String userId) {
        long now = (new Date()).getTime();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // 로그로 토큰을 출력
        log.info("Access Token: {}", accessToken);
        log.info("Refresh Token: {}", refreshToken);

        return JwtDTO.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {

        if (token == null || !token.startsWith("Bearer ")) {
            return false;
        }

        token = token.substring(7);

        if (userService.isBlacklisted(token)) {
            return false;
        }


        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty", e);
        }
        return false;
    }

    // 토큰을 파싱하여 Claims 정보를 가져오는 메서드
    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Date getExpirationDate(String token) {
        return parseClaims(token).getExpiration();
    }

    // JWT 토큰에서 userId(= subject) 문자열 추출하는 메서드
    public String extractUserId(String token) {

        token = token.substring(7);

        Claims claims = parseClaims(token);
        return claims.getSubject();
    }


}