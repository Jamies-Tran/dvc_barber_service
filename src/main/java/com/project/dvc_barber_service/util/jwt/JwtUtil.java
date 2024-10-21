package com.project.dvc_barber_service.util.jwt;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtUtil {
    @Value("${app.access-token.secret-key}")
    String secretKey;

    @Value("${app.access-token.expire-time}")
    Integer accessExpiredDuration;

    public String generateAccessToken(String phone) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .subject(phone)
                .expiration(accessTokenExpiredAt())
                .issuedAt(new Date())
                .compact();

    }

    public Date accessTokenExpiredAt() {
        return Date.from(Instant.now().plus(accessExpiredDuration, ChronoUnit.MINUTES));
    }

    public String getIdentityFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")) {
            return getUserPhoneFromToken(token);
        }

        return null;
    }

    public String getUserPhoneFromToken(String token) {
        return Jwts.parser()
                .decryptWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .decryptWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseEncryptedClaims(token);
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }
}
