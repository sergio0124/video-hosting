package com.example.demo.controller;

import com.example.demo.domain.UserCreateJsonRequest;
import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final UserMapper userMapper;

    @PostMapping("/app/user/block")
    public void blockUser(@RequestParam UUID userId) {
	userService.blockUser(userId);
    }

    @GetMapping("/app/users")
    public List<UserResponse> getUsers(@RequestParam(required = false) String search) {
	return userService.getUsers(search);
    }

    @PostMapping(value = "/app/user", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public UserResponse createUser(@RequestBody UserCreateRequest request) throws IOException {
	if (!userService.isUsernameUnique(request.getUsername())) {
	    throw new BadRequestException("Пользователь с таким логином уже существует");
	}
	return userService.createUser(request);
    }

    @PutMapping(value = "/app/user", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public UserResponse updateUser(@RequestBody UserUpdateRequest request) throws IOException {
	return userService.updateUser(request);
    }

    @GetMapping("/app/user")
    public UserResponse getUser(@AuthenticationPrincipal UserEntity user) {
	return userMapper.doMap(user);
    }

    @PostMapping("/app/users/json")
    public void createUserJson(@RequestBody UserCreateJsonRequest request) throws IOException {
	userService.createUsers(request);
    }
}
