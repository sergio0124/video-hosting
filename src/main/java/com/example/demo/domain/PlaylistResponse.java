package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistResponse {

    private UUID id;

    private String name;

    private String creationDate;

    private Boolean isPublic;

    private String description;

    private Integer videoCount;

    private Integer visitsCount;

    private List<VideoResponse> videoResponses;

    private UserResponse userResponse;
}
