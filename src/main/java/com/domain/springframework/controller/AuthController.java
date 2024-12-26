package com.domain.springframework.controller;

import com.domain.springframework.dto.request.LoginBody;
import com.domain.springframework.exception.AuthenticationException;
import com.domain.springframework.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
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


    /**
     * Login to the application
     * @param loginBody a request body containing email and password
     * @return a response entity containing the token
     */
    @Operation(summary = "Login", description = "Login to the application")
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
