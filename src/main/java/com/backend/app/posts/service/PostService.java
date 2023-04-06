package com.backend.app.posts.service;

import com.backend.app.posts.entity.Post;

import java.util.List;

public interface PostService {
    public List<Post> getAllPosts();

    public Post getPostById(Long id);

    public Post createPost(Post post);

    public void deletePostById(Long id);
}
