package com.example.springframework.utility;

import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
@Getter
@Setter
public class AppProperties {
    @Value("${secret}")
    private String jwtKey;

    public SecretKey getJwtKey() {
        byte[] decodedKey = Base64.getDecoder().decode(jwtKey);
        return Keys.hmacShaKeyFor(decodedKey);
    }
}
