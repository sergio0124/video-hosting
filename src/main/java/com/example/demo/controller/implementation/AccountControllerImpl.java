package com.example.demo.controller.implementation;

import com.example.demo.controller.AccountController;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountControllerImpl implements AccountController {

    private final UserService service;

    @Override
    public void updateUser(UserEntity user, UserUpdateRequest request) {
        log.debug("Update user '{}'", request.getUsername());
        service.updateUser(user, request);
    }

    @Override
    public UserResponse getUserInfo(UserEntity user) {
        log.debug("Get info on user '{}'", user.getUsername());
        return service.getUserInformation(user.getUsername());
    }
}
