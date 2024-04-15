package com.example.demo.controller.implementation;

import com.example.demo.controller.UserController;
import com.example.demo.domain.UserRegisterRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService service;

    @Override
    public void createUser(UserRegisterRequest request) {
        log.debug("create user with username '{}'", request.getUsername());
        service.createUser(request);
    }

    @Override
    public List<UserResponse> getUsers(String searchString, Integer page, Integer limit) {
        log.debug("search user by string '{}'", searchString);
        return service.searchUsers(searchString, page, limit);
    }

    @Override
    public void deleteUser(String username) {
        log.debug("delete user by username '{}'", username);
        service.deleteUser(username);
    }

    @Override
    public void updateUser(UserEntity user, UserUpdateRequest request) {
        service.updateUser(user, request);
    }
}
