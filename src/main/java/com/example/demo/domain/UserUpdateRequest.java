package com.example.demo.domain;

import com.example.demo.domain.entity.enums.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotNull
    private UUID id;

    private String username;

    private String mail;

    private String fullname;

    private Role role;

    private String status;
}
