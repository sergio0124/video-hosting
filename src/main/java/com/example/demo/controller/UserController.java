package com.example.demo.controller;

import com.example.demo.domain.UserRegisterRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Tag(name = "user admin", description = "API для работы с пользователями")
@Validated
public interface UserController {

    @Operation(summary = "Создание пользователя администратором")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PostMapping(value = "api/v1/admin/user")
    void createUser(@RequestBody @Valid UserRegisterRequest request);

    @Operation(summary = "Получение пользователя")
    @GetMapping(value = "api/v1/admin/users/{searchString}")
    List<UserResponse> getUsers(@PathVariable String searchString,
                                @RequestParam(defaultValue = "0") @Min(0) Integer page,
                                @RequestParam(defaultValue = "10") @Min(10) Integer limit);

    @Operation(summary = "Удаление пользователя")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "api/v1/admin/user")
    void deleteUser(@RequestParam String username);

    @Operation(summary = "Изменение пользователя администратором")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping(value = "api/v1/admin/user")
    void updateUser(@AuthenticationPrincipal UserEntity user,
                    @RequestBody @Valid UserUpdateRequest request);
}
