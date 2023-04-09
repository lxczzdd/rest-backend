package com.backend.app.posts.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PostCreateDTO implements Serializable {

    @Schema(example = "My First Post")
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Schema(example = "Some content")
    private String content;

    @Schema(type = "string", example = "coolimage.png")
    private MultipartFile file;

}
