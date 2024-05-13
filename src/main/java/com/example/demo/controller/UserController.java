package com.example.demo.controller;

import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/app/user/block")
    public void blockUser(@RequestParam UUID userId) {
	userService.blockUser(userId);
    }

    @GetMapping("/app/users")
    public List<UserResponse> getUsers(@RequestParam(required = false) String search) {
	return userService.getUsers(search);
    }

    @PostMapping("/app/user")
    public void createUser(@RequestBody UserCreateRequest request) throws IOException {
	userService.createUser(request);
    }

    @PutMapping("/app/user")
    public void updateUser(@RequestBody UserUpdateRequest request) throws IOException {
	userService.updateUser(request);
    }
}
