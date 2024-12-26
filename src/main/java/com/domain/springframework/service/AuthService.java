package com.domain.springframework.service;

import com.domain.springframework.dto.response.LoginDBResponse;
import com.domain.springframework.repository.AuthRepository;
import com.domain.springframework.utility.AppProperties;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final AuthRepository authRepository;
    private final AppProperties appProperties;

    @Autowired
    public AuthService(final AuthRepository authRepository, AppProperties applicationProperties) {
        this.authRepository = authRepository;
        this.appProperties = applicationProperties;
    }

    public String login(String email, String password) {

        // Fetch user details by email
        Optional<LoginDBResponse> user = authRepository.getUserRoleByEmail(email);
        SecretKey secretKey = appProperties.getJwtKey();

        // Return null if user not found
        if (user.isEmpty()) {
            return null;
        }

        LoginDBResponse dbUser = user.get();

        // Verify the password
        if (!new BCryptPasswordEncoder().matches(password, dbUser.getPassword())) {
            return null;
        }

        // Generate JWT token
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .claim("role", dbUser.getRole())
                .signWith(secretKey)
                .compact();
    }

}
