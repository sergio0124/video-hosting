package com.example.demo.controller;

import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Tag(name = "accounts", description = "API для манипуляций аккаунтом")
@Validated
public interface AccountController {

    @Operation(summary = "Изменение аккаунта пользователя")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "api/v1/account")
    void updateUser(@AuthenticationPrincipal UserEntity user,
                    @RequestBody @Valid UserUpdateRequest request);

    @Operation(summary = "Получение информации о себе")
    @GetMapping(value = "api/v1/account")
    UserResponse getUserInfo(@AuthenticationPrincipal UserEntity user);

}
