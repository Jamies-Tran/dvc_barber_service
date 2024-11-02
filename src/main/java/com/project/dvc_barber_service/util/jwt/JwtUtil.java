package com.project.dvc_barber_service.util.jwt;

import com.project.dvc_barber_service.config.handler.exception.IdentificationException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bouncycastle.jcajce.BCFKSLoadStoreParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;

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

            return getUserPhoneFromToken(token.substring(7));
        }

        return null;
    }

    public String getUserPhoneFromToken(String token) {
        return Jwts.parser()
                .verifyWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public void validateToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if(StringUtils.hasText(token) && token.startsWith("Bearer")) {
            try {
                Jwts.parser()
                        .verifyWith(new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName()))
                        .build()
                        .parseSignedClaims(token.substring(7));
            } catch (ExpiredJwtException e) {
                throw new IdentificationException(e.getMessage());
            }
        }

    }
}
