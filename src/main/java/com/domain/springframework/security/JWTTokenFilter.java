package com.domain.springframework.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {
    private final TokenParser tokenParser;

    @Autowired
    public JWTTokenFilter(final TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = request.getHeader("X-Auth-Token");
        if (token == null || token.isEmpty()) {
            chain.doFilter(request, response); // Skip if no token
            return;
        }

        try {
            Claims claims = tokenParser.parseToken(token);
            String role = claims.get("role", String.class);

            CurrentUserDetails userDetails = CurrentUserDetails.builder()
                    .email(claims.getSubject())
                    .role(Role.valueOf(role))
                    .token(token)
                    .build();

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.info("Authentication set with authorities: " + authentication.getAuthorities());
        } catch (Exception e) {
            logger.error("JWT validation failed: " + e.getMessage(), e);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }




}
