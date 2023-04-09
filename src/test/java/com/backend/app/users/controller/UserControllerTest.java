package com.backend.app.users.controller;

import com.backend.app.users.dto.UserCreateAndReplaceDTO;
import com.backend.app.users.entity.User;
import com.backend.app.users.exception.UserNotFoundException;
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

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    private static final long ID = 1L;

    @Mock
    private UserService service;

    @InjectMocks
    private UserController controller;

    @Test
    void getOneUserById_shouldCallService() {
        final User user = Mockito.mock(User.class);
        Mockito.when(service.getUserById(ID)).thenReturn(user);

        final User actual = controller.getUserById(ID);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(user, actual);
        Mockito.verify(service).getUserById(ID);
    }

    @Test
    void getOneUserById_shouldThrowException_whenDoesNotExist() {
        Mockito.when(service.getUserById(ID)).thenThrow(UserNotFoundException.class);

        Assertions.assertThrows(UserNotFoundException.class, () -> controller.getUserById(ID));
    }

    @Test
    void getAllUsers_shouldCallService() {
        List<User> users = new ArrayList<>();
        for(int i = 0; i < 3; ++i)
            users.add(Mockito.mock(User.class));
        Mockito.when(service.getAllUsers()).thenReturn(users);

        final List<User> actualUsers = controller.getAllUsers();

        Assertions.assertNotNull(actualUsers);
        Assertions.assertEquals(users, actualUsers);
        Mockito.verify(service).getAllUsers();
    }

    @Test
    void createUser_shouldCallService() {
        final UserCreateAndReplaceDTO user = Mockito.mock(UserCreateAndReplaceDTO.class);

        controller.createUser(user);

        Mockito.verify(service).createUser(user);
    }

    @Test
    void deleteUserById_shouldCallService() {
        controller.deleteUser(ID);

        Mockito.verify(service).deleteUserById(ID);
    }

    @Test
    void replaceUser_shouldCallService() {
        final UserCreateAndReplaceDTO user = Mockito.mock(UserCreateAndReplaceDTO.class);

        controller.replaceUser(user, ID);

        Mockito.verify(service).replaceUser(user, ID);
    }
}