package com.backend.app.posts.entity;

import com.backend.app.users.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "23")
    private Long id;

    @Column(nullable = false)
    @Schema(example = "My First Post")
    private String title;

    @Column
    @Schema(example = "Some content")
    private String content;

    @Column
    @Schema(example = "coolimage.png")
    private String file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public Post(String title, String content, String file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }
}
