package com.example.demo.domain;

import com.example.demo.domain.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema(name = "Запрос на регистрацию пользователя администратором")
public class UserRegisterRequest {

    @NotBlank
    @Schema(name = "логин пользователя",
            required = true)
    private String username;

    @NotBlank
    @Schema(name = "пароль",
            required = true)
    private String password;

    @NotBlank
    @Schema(name = "ФИО пользователя",
            required = true)
    private String fullname;

    @NotBlank
    @Schema(name = "Роль пользователя",
            required = true,
            example = "TEACHER/STUDENT")
    private Role role;

    @Schema(name = "Статус пользователя")
    private String status;
}
