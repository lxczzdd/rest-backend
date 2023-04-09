package com.backend.app.users.entity;

import com.backend.app.posts.entity.Post;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "users")
@Tag(name = "User entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(example = "1")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(example = "Bestuser134")
    private String username;

    @Column(nullable = false, unique = true)
    @Schema(example = "Bestuser134")
    private String email;

    @Column(nullable = false)
    @Schema(example = "Bestuser134")
    private String password;

    @CreationTimestamp
    private Instant createdAt;

    @OneToMany(mappedBy="user")
    @Hidden
    private List<Post> posts;


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }



}
