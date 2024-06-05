package com.example.demo.domain;

import com.example.demo.domain.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {

    @NotBlank
    private UUID id;

    private String username;

    private MultipartFile image;

    private String mail;

    private String fullname;

    private Role role;

    private String status;

    private String password;
}
