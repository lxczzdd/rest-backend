package com.backend.app.posts.service;

import com.backend.app.posts.entity.Post;
import com.backend.app.posts.exception.PostNotFoundException;
import com.backend.app.posts.repository.PostRepository;
import com.backend.app.users.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private PostRepository repository;

    @Mock
    private UserService userService;

    @InjectMocks
    private PostServiceImpl service;

    @Test
    void getOnePostById_shouldCallRepository() {
        final Post post = Mockito.mock(Post.class);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(post));

        final Post actual = service.getPostById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(post, actual);
        Mockito.verify(repository).findById(ID);
    }

    @Test
    void getOnePostById_shouldThrowException_whenDoesNotExist() {
        Assertions.assertThrows(PostNotFoundException.class, () -> service.getPostById(ID));
    }

    @Test
    void getAllPosts_shouldCallRepository() {
        List<Post> posts = new ArrayList<>();
        for(int i = 0; i < 3; ++i)
            posts.add(Mockito.mock(Post.class));
        Mockito.when(repository.findAll()).thenReturn(posts);

        final List<Post> actualPosts = service.getAllPosts();

        Assertions.assertNotNull(actualPosts);
        Assertions.assertEquals(posts, actualPosts);
        Mockito.verify(repository).findAll();
    }
}