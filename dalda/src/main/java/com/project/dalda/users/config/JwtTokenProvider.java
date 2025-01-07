package com.project.dalda.users.config;

import com.project.dalda.users.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    private final String SECRET_KEY;
    private final long ACCESS_TOKEN_EXPIRATION = 1000L * 60 * 30; // 30분
    private final long REFRESH_TOKEN_EXPIRATION = 1000L * 60 * 60 * 24 * 7; // 1주일

    public JwtTokenProvider(@Value("${jwt.secretKey}") String secretKey) {
        this.SECRET_KEY = Base64.getUrlEncoder().withoutPadding().encodeToString(secretKey.getBytes());
        System.out.println("Encoded Secret Key: " + this.SECRET_KEY); // 디버깅용
    }

    public String createAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", user.getEmail());
        claims.put("nickname", user.getNickname());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String createRefreshToken() {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        System.out.println("token: " + token);
        if(token.contains("Bearer ")) {
            token = token.substring(7);
        }
        System.out.println("token: " + token);
        String subject = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody()
                .getSubject();
        System.out.println("subject: " + subject);
        return subject;
    }
}
