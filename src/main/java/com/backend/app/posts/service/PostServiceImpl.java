package com.backend.app.posts.service;

import com.backend.app.posts.entity.FileUploadUtil;
import com.backend.app.posts.entity.Post;
import com.backend.app.posts.entity.dto.PostCreateDTO;
import com.backend.app.posts.exception.PostNotFoundException;
import com.backend.app.posts.exception.PostPermissionsException;
import com.backend.app.posts.repository.PostRepository;
import com.backend.app.users.entity.User;
import com.backend.app.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("Could not find post with id " + id));
    }

    @Override
    public Post createPost(PostCreateDTO postDTO) {
        String username = getUsernameFromAuth();
        User user = userService.getUserByUsername(username);
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setUser(user);
        String fileName = "";
        if(postDTO.getFile() != null)
            fileName= StringUtils.cleanPath(postDTO.getFile().getOriginalFilename());

        String filecode = null;
        try {
            filecode = FileUploadUtil.saveFile(fileName, postDTO.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        post.setFile("/file/" + filecode + "-" + fileName);



        return postRepository.save(post);
    }

    @Override
    public void deletePostById(Long id) {
        String username = getUsernameFromAuth();
        Post post = getPostById(id);
        if(post.getUser().getUsername().equals(username))
            postRepository.deleteById(id);
        else
            throw new PostPermissionsException("You can't delete other users post");
    }

    private String getUsernameFromAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
