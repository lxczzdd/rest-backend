package com.backend.app.auth.controller;

import com.backend.app.auth.api.JwtRequest;
import com.backend.app.auth.api.JwtResponse;
import com.backend.app.auth.service.AuthService;
import com.backend.app.users.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        authService.register(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(new JwtResponse(authService.login(authenticationRequest)));
    }
}
