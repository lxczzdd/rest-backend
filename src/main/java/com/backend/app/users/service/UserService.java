package com.backend.app.users.service;

import com.backend.app.users.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByUsername(String username);

    public User createUser(User user);

    public void deleteUserById(Long id);

    public User replaceUser(User user, Long id);
}
