package com.backend.app.auth.service;

import com.backend.app.auth.api.JwtRequest;
import com.backend.app.config.JwtTokenUtil;
import com.backend.app.config.JwtUserDetailsService;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private JwtUserDetailsService userDetailsService;

    @InjectMocks
    private AuthServiceImpl service;

    @Test
    void register_shouldCallUserRepository_save() {
        final User user = Mockito.mock(User.class);
        Mockito.when(userRepo.save(user)).thenReturn(user);

        final User actual = userRepo.save(user);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(user, actual);
        Mockito.verify(userRepo).save(user);
    }

    @Test
    void login_shouldCallAuthManager() {
        final JwtRequest request = Mockito.mock(JwtRequest.class);
        final Authentication expectedAuth = Mockito.mock(Authentication.class);
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        Mockito.when(authenticationManager.authenticate(authenticationToken)).thenReturn(expectedAuth);

        Authentication actualAuth = authenticationManager.authenticate(authenticationToken);

        Assertions.assertNotNull(expectedAuth);
        Assertions.assertEquals(expectedAuth, actualAuth);
        Mockito.verify(authenticationManager).authenticate(authenticationToken);
    }

    @Test
    void login_shouldCallUserDetails() {
        final JwtRequest request = Mockito.mock(JwtRequest.class);
        final UserDetails expectedUserDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetailsService.loadUserByUsername(request.getUsername())).thenReturn(expectedUserDetails);

        UserDetails actualUserDetails = userDetailsService.loadUserByUsername(request.getUsername());

        Assertions.assertNotNull(expectedUserDetails);
        Assertions.assertEquals(expectedUserDetails, actualUserDetails);
        Mockito.verify(userDetailsService).loadUserByUsername(request.getUsername());
    }

    @Test
    void login_shouldCallJwtTokenUtil() {
        final UserDetails userDetails = Mockito.mock(UserDetails.class);
        final String expectedToken = "token";
        Mockito.when(jwtTokenUtil.generateToken(userDetails)).thenReturn(expectedToken);

        String actualToken = jwtTokenUtil.generateToken(userDetails);

        Assertions.assertNotNull(actualToken);
        Assertions.assertEquals(expectedToken, actualToken);
        Mockito.verify(jwtTokenUtil).generateToken(userDetails);
    }

    @Test
    void login_shouldThrowUserNotFoundException_whenAuthIsNotAuthenticated() {
        final JwtRequest request = Mockito.mock(JwtRequest.class);
        final Authentication authentication = Mockito.mock(Authentication.class);

        Mockito.when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()))).thenReturn(authentication);
        Mockito.when(authentication.isAuthenticated()).thenReturn(false);

        Assertions.assertThrows(UserNotFoundException.class, () -> service.login(request));
    }

}