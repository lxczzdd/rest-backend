package com.backend.app.auth.api;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(example = "Bestuser124")
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Schema(example = "Be5252154")
    private String password;

}
