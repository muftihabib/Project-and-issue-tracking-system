package com.example.PaITS.user.service;

import com.example.PaITS.user.dto.CreateUserRequest;
import com.example.PaITS.user.dto.LoginRequest;
import com.example.PaITS.user.dto.UpdateUserRequest;
import com.example.PaITS.user.dto.UserResponse;
import com.example.PaITS.user.entity.Role;
import com.example.PaITS.user.entity.User;
import com.example.PaITS.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPasswordHash(request.getPassword());
        user.setRole(Role.MEMBER);
        user.setActive(true);

        User saved = userRepository.save(user);

        return mapToResponse(saved);
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        if (!user.isActive()) {
            throw new RuntimeException("Account is inactive");
        }

        return "Login successful";
    }

    public UserResponse updateUser(UUID id, UpdateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getFullName() != null)
            user.setFullName(request.getFullName());

        if (request.getUsername() != null)
            user.setUsername(request.getUsername());

        if (request.getPassword() != null)
            user.setPasswordHash(request.getPassword());

        if (request.getAvatarUrl() != null)
            user.setAvatarUrl(request.getAvatarUrl());

        User updated = userRepository.save(user);
        return mapToResponse(updated);
    }

    public UserResponse getUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    public void deactivateUser(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);
        userRepository.save(user);
    }

    private UserResponse mapToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.isActive(),
                user.getAvatarUrl()
        );
    }

}
