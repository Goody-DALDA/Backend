package com.project.dalda.users.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

    private final String secretKey;
    private final long expirationTime;

    // @Value로 Spring Boot 설정 값 주입
    public JwtTokenUtil(
            @Value("${jwt.secretKey}") String secretKey,
            @Value("${jwt.expiration-time}") long expirationTime) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalStateException("Secret Key is not configured!");
        }
        this.secretKey = secretKey;
        this.expirationTime = expirationTime;
        System.out.println("Loaded Secret Key: " + this.secretKey); // 디버깅용 출력
    }


    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
