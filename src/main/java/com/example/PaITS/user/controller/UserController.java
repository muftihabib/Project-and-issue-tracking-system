package com.example.PaITS.user.controller;


import com.example.PaITS.user.dto.CreateUserRequest;
import com.example.PaITS.user.dto.LoginRequest;
import com.example.PaITS.user.dto.UserResponse;
import com.example.PaITS.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {

        return ResponseEntity.ok(userService.createUser(request));

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(
                userService.login(request)
        );
    }




}

