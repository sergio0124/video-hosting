package com.example.demo.domain;

import com.example.demo.domain.entity.enums.Role;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class UserResponse {

    private UUID id;

    private String username;

    private String imageUrl;

    private String status;

    private String mail;

    private String fullname;

    private String registrationDate;

    private String role;

    private boolean blocked;
}
