package com.backend.app.posts.controller;

import com.backend.app.posts.entity.Post;
import com.backend.app.posts.entity.dto.PostCreateDTO;
import com.backend.app.posts.exception.PostNotFoundException;
import com.backend.app.posts.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PostControllerTest {
    private static final long ID = 1L;

    @Mock
    private PostService service;

    @InjectMocks
    private PostController controller;

    @Test
    void getOnePostById_shouldCallService() {
        final Post post = Mockito.mock(Post.class);
        Mockito.when(service.getPostById(ID)).thenReturn(post);

        final Post actual = controller.getPostById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(post, actual);
        Mockito.verify(service).getPostById(ID);
    }

    @Test
    void getOnePostById_shouldThrowException_whenDoesNotExist() {
        Mockito.when(service.getPostById(ID)).thenThrow(PostNotFoundException.class);

        Assertions.assertThrows(PostNotFoundException.class, () -> controller.getPostById(ID));
    }

    @Test
    void getAllPosts_shouldCallService() {
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < 3; ++i)
            posts.add(Mockito.mock(Post.class));
        Mockito.when(service.getAllPosts()).thenReturn(posts);

        final List<Post> actualPosts = controller.getAllPosts();

        Assertions.assertNotNull(actualPosts);
        Assertions.assertEquals(posts, actualPosts);
        Mockito.verify(service).getAllPosts();
    }

    @Test
    void createPost_shouldCallService() {
        final PostCreateDTO postDTO = Mockito.mock(PostCreateDTO.class);

        controller.createPost(postDTO);

        Mockito.verify(service).createPost(postDTO);
    }

    @Test
    void deletePostById_shouldCallService() {
        controller.deletePost(ID);

        Mockito.verify(service).deletePostById(ID);
    }


}