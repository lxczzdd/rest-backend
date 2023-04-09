package com.backend.app.auth.service;

import com.backend.app.auth.api.JwtRequest;
import com.backend.app.auth.exception.AuthAlreadyExistException;
import com.backend.app.config.JwtTokenUtil;
import com.backend.app.config.JwtUserDetailsService;
import com.backend.app.users.dto.UserCreateAndReplaceDTO;
import com.backend.app.users.entity.User;
import com.backend.app.users.exception.UserNotFoundException;
import com.backend.app.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;


    @Override
    public User register(UserCreateAndReplaceDTO dto) {
        User user = new User(dto.getUsername(), dto.getEmail(), new BCryptPasswordEncoder().encode(dto.getPassword()));
        if(userRepo.findByUsername(user.getUsername()).isPresent())
            throw new AuthAlreadyExistException("User with this credentials already exist");

        return userRepo.save(user);
    }

    @Override
    public String login(JwtRequest authenticationRequest) {
        Authentication auth = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        if(!auth.isAuthenticated())
            throw new UserNotFoundException("User with this credentials not found");

        return token;
    }
}
