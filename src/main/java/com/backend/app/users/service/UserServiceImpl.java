package com.backend.app.users.service;

import com.backend.app.users.dto.UserCreateAndReplaceDTO;
import com.backend.app.users.entity.User;
import com.backend.app.users.exception.UserNotFoundException;
import com.backend.app.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Could not find user with id " + id));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Could not find user with username " + username));
    }

    @Override
    public User createUser(UserCreateAndReplaceDTO dto) {
        User user = new User(dto.getUsername(), dto.getEmail(), dto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User replaceUser(UserCreateAndReplaceDTO dto, Long id) {
        return userRepository.findById(id)
                .map(toUpdateUser -> {
                    toUpdateUser.setUsername(dto.getUsername());
                    toUpdateUser.setEmail(dto.getEmail());
                    toUpdateUser.setPassword(dto.getPassword());
                    return userRepository.save(toUpdateUser);
                }).get();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
