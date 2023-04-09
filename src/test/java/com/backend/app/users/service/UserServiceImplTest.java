package com.backend.app.users.service;

import com.backend.app.users.dto.UserCreateAndReplaceDTO;
import com.backend.app.users.entity.User;
import com.backend.app.users.exception.UserNotFoundException;
import com.backend.app.users.repository.UserRepository;
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
class UserServiceImplTest {

    private static final long ID = 1L;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void getOneUserById_shouldCallRepository() {
        final User user = Mockito.mock(User.class);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(user));

        final User actual = service.getUserById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(user, actual);
        Mockito.verify(repository).findById(ID);
    }

    @Test
    void getOneUserById_shouldThrowException_whenDoesNotExist() {
        Assertions.assertThrows(UserNotFoundException.class, () -> service.getUserById(ID));
    }

    @Test
    void getAllUsers_shouldCallRepository() {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 3; ++i)
            users.add(Mockito.mock(User.class));
        Mockito.when(repository.findAll()).thenReturn(users);

        final List<User> actualUsers = service.getAllUsers();

        Assertions.assertNotNull(actualUsers);
        Assertions.assertEquals(users, actualUsers);
        Mockito.verify(repository).findAll();
    }

    @Test
    void createUser_shouldCallRepository() {
        final UserCreateAndReplaceDTO dto = Mockito.mock(UserCreateAndReplaceDTO.class);
        final User user = Mockito.mock(User.class);
        service.createUser(dto);

        Mockito.verify(repository).save(user);
    }

    @Test
    void deleteUserById_shouldCallRepository() {
        final User user = Mockito.mock(User.class);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(user));

        service.deleteUserById(ID);

        Mockito.verify(repository).delete(user);
    }

    @Test
    void replaceUser_shouldCallRepository() {
        final UserCreateAndReplaceDTO updatedUser = new UserCreateAndReplaceDTO("newName", "newemail@example.com", "252525");
        final User user = Mockito.mock(User.class);
        Mockito.when(repository.findById(ID)).thenReturn(Optional.of(user));
        Mockito.when(repository.save(user)).thenReturn(new User(updatedUser.getUsername(), updatedUser.getEmail(), updatedUser.getPassword()));

        final User actual = service.replaceUser(updatedUser, ID);

        Assertions.assertNotNull(actual);
        Assertions.assertNotEquals(user, actual);
        Mockito.verify(repository).save(user);
    }
}