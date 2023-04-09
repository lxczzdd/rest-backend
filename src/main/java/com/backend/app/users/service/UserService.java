package com.backend.app.users.service;

import com.backend.app.users.dto.UserCreateAndReplaceDTO;
import com.backend.app.users.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User getUserByUsername(String username);

    User replaceUser(UserCreateAndReplaceDTO dto, Long id);

    public void deleteUserById(Long id);

    User createUser(UserCreateAndReplaceDTO dto);

}
