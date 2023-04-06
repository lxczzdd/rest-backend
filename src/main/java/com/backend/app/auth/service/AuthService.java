package com.backend.app.auth.service;

import com.backend.app.auth.api.JwtRequest;
import com.backend.app.users.entity.User;

public interface AuthService {
    public User register(User user);
    public String login(JwtRequest authenticationRequest) throws Exception;
}
