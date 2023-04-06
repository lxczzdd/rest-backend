package com.backend.app.posts.service;

import com.backend.app.config.JwtTokenUtil;
import com.backend.app.posts.entity.Post;
import com.backend.app.posts.exception.PostNotFoundException;
import com.backend.app.posts.repository.PostRepository;
import com.backend.app.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Could not find post with id " + id));
    }

    @Override
    public Post createPost(Post post) {
        final String requestTokenHeader = ("Authorization");
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }


}
