package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponse {

    private UUID id;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Timestamp creationDate;

    private String description;

    private Integer count;

    private List<UserResponse> users = new ArrayList<>();
}
