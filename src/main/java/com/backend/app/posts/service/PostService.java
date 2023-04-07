package com.backend.app.posts.service;

import com.backend.app.posts.entity.Post;
import com.backend.app.posts.entity.dto.PostCreateDTO;

import java.util.List;

public interface PostService {
    public List<Post> getAllPosts();

    public Post getPostById(Long id);

    public Post createPost(PostCreateDTO postCreateDTO);

    public void deletePostById(Long id);
}
