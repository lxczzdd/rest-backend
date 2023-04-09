package com.backend.app.users.controller;

import com.backend.app.users.dto.UserCreateAndReplaceDTO;
import com.backend.app.users.entity.User;
import com.backend.app.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Tag(name="User Controller", description="Controller for CRUD operations with user entity")
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))})})
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Get one user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the user",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Create user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@Valid @RequestBody UserCreateAndReplaceDTO dto) {
        return userService.createUser(dto);
    }


    @Operation(summary = "Replace user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User replaced",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "400", description = "Not found")})
    @PutMapping("/{id}")
    public User replaceUser(@Valid @RequestBody UserCreateAndReplaceDTO dto, @PathVariable Long id) {
        return userService.replaceUser(dto, id);
    }

    @Operation(summary = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "Not found")})
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }
}
