package com.example.demo.domain;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileUpdateRequest {

    @NotNull
    private UUID id;

    private String username;

    private String mail;

    private String fullname;

    private String status;

    private String oldPass;

    private String newPass;
}
