package com.backend.app.auth.controller;

import com.backend.app.auth.api.JwtRequest;
import com.backend.app.auth.api.JwtResponse;
import com.backend.app.auth.service.AuthService;
import com.backend.app.users.dto.UserCreateAndReplaceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Tag(name="Authentication Controller", description="Controller for register and login for users")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtRequest.class))}),
            @ApiResponse(responseCode = "409", description = "User already exist",
                    content = @Content)})
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreateAndReplaceDTO dto) {
        authService.register(dto);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Operation(summary = "Login user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get auth token",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = JwtResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content)})
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(new JwtResponse(authService.login(authenticationRequest)));
    }
}
