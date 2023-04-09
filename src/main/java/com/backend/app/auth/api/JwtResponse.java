package com.backend.app.auth.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class JwtResponse implements Serializable {
    @Schema(example = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhc2Rhc2QiLCJpYXQiOjE2ODEwNDA3MzQsImV4cCI6MTY4MTA3NjczNH0." +
                    "7ijfrUaCGbWQ9Er3OnjR9JD6xADUA34xX-JXCY9??????????????????")
    private final String token;
}
