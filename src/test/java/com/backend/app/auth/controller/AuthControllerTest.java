package com.backend.app.auth.controller;

import com.backend.app.auth.api.JwtRequest;
import com.backend.app.auth.api.JwtResponse;
import com.backend.app.auth.service.AuthService;
import com.backend.app.users.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService service;

    @InjectMocks
    private AuthController controller;

    @Test
    void register_shouldCallService() {
        final User user = Mockito.mock(User.class);

        controller.register(user);

        Mockito.verify(service).register(user);
    }

    @Test
    void register_shouldReturnResponseEntity() {
        final User user = Mockito.mock(User.class);

        ResponseEntity<?> actual = controller.register(user);
        ResponseEntity<?> expected = new ResponseEntity<>("User registered successfully!", HttpStatus.OK);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void login_shouldCallService() throws Exception {
        final JwtRequest request = Mockito.mock(JwtRequest.class);

        controller.login(request);

        Mockito.verify(service).login(request);
    }

    @Test
    void login_shouldReturnDifferentTokenResponseEntity() throws Exception {
        final JwtRequest request = Mockito.mock(JwtRequest.class);

        ResponseEntity<?> actual = controller.login(request);
        ResponseEntity<?> expected = new ResponseEntity<>(new JwtResponse("token"), HttpStatus.OK);

        Assertions.assertEquals(expected.getStatusCode(), actual.getStatusCode());
        Assertions.assertNotEquals(expected, actual);
    }
}