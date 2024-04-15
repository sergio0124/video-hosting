package com.example.demo.domain;

import com.example.demo.domain.entity.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(name = "Данные пользователя")
public class UserResponse {

    @Schema(name = "id пользователя")
    private UUID id;

    @Schema(name = "логин пользователя")
    private String username;

    @Schema(name = "ФИО пользователя")
    private String fullname;

    @Schema(name = "роль пользователя")
    private Role role;

    @Schema(name = "статус пользователя")
    private String status;
}
