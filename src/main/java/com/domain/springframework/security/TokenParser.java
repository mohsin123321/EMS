package com.domain.springframework.security;

import com.domain.springframework.utility.AppProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;


@Component
public class TokenParser {
    private final JwtParser jwtParser;
    private static final long SECONDS = 60 * 60; // 60 minutes


    public TokenParser(AppProperties appProperties) {
        SecretKey secretKey = appProperties.getJwtKey(); // Access secret key from AppProperties
        this.jwtParser = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .setAllowedClockSkewSeconds(SECONDS)
                .build();
    }

    public Claims parseToken(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }
}
