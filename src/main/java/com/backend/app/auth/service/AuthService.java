package com.backend.app.auth.service;

import com.backend.app.users.entity.User;

public interface AuthService {
    public User register(User user);
    public void login(String username, String password) throws Exception;
}
