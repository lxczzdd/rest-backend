package com.backend.app.posts.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class PostCreateDTO implements Serializable {

    private String title;

    private String content;

    private MultipartFile file;

}
