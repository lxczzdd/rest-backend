package com.backend.app.posts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getFileByFilename(@PathVariable String filename) throws IOException {
        File file = new File("src/main/resources/static/" + filename);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(Files.readAllBytes(file.toPath()));
    }
}
