package com.backend.app.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class UserCreateAndReplaceDTO implements Serializable {

    @NotBlank(message = "Name is mandatory")
    @Schema(example = "Bestuser134")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email form")
    @Schema(example = "bestuser124@example.com")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Schema(example = "123456")
    private String password;
}
