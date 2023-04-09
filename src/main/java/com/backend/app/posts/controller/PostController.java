package com.backend.app.posts.controller;

import com.backend.app.posts.entity.Post;
import com.backend.app.posts.entity.dto.PostCreateDTO;
import com.backend.app.posts.service.PostService;
import com.backend.app.users.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Tag(name="Post Controller", description="Controller for CRUD operations with post entity")
public class PostController {

    private final PostService postService;

    @Operation(summary = "Get all posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of posts",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))})})
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @Operation(summary = "Get one post by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the post",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }


    @Operation(summary = "Create post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(@Valid @ModelAttribute PostCreateDTO postDTO) {
        return postService.createPost(postDTO);
    }

    @Operation(summary = "Delete post by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Post deleted",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable Long id) {
        postService.deletePostById(id);
    }
}
