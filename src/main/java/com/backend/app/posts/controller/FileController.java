package com.backend.app.posts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="File Controller", description="Controller for get files from static")
public class FileController {

    @Operation(summary = "Get one file by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the picture",
                    content = {@Content(mediaType = "image/png")}),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getFileByFilename(@PathVariable String filename) throws IOException {
        File file = new File("src/main/resources/static/" + filename);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(Files.readAllBytes(file.toPath()));
    }
}
