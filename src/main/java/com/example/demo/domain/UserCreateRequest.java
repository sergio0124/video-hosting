package com.example.demo.domain;

import com.example.demo.domain.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCreateRequest {

    private String username;

    private MultipartFile image;

    private String mail;

    private String fullname;

    private Role role;

    private String status;
}
