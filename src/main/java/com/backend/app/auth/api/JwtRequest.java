package com.backend.app.auth.api;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class JwtRequest implements Serializable {

    @NotBlank(message = "Name is mandatory")
    private String username;

    @NotBlank(message = "Password is mandatory")
    private String password;

}
