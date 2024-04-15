package com.example.demo.domain;

import com.example.demo.domain.entity.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Schema(name = "Запрос на изменение пользователя")
public class UserUpdateRequest {

    @Schema(name = "id пользователя",
            example = "04fc55ae-e19c-4ccc-a89d-dee24ccc3d6d")
    private UUID id;

    @NotBlank
    @Schema(name = "Логин пользователя",
    required = true)
    private String username;

    @NotBlank
    @Schema(name = "пароль пользователя",
            required = true)
    private String password;

    @NotBlank
    @Schema(name = "ФИО пользователя",
            required = true)
    private String fullname;

    @NotBlank
    @Schema(name = "Роль пользователя",
            required = true)
    private Role role;

    @Schema(name = "Статус пользователя")
    private String status;
}
