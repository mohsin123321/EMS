package com.example.springframework.controller;

import com.example.springframework.dto.request.LoginBody;
import com.example.springframework.exception.AuthenticationException;
import com.example.springframework.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${service.endpoint}")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@Valid @RequestBody LoginBody loginBody) {
        String email = loginBody.getEmail();
        String password = loginBody.getPassword();
        String token = authService.login(email, password);
        if (token != null) {
            return ResponseEntity.ok().body(token);
        } else {
            throw new AuthenticationException("Invalid email or password");
        }
    }
}
