package com.example.demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class PlaylistResponse {

    private UUID id;

    private String name;

    private Timestamp creationDate;

    private String description;

    private Integer videoCount;

    private List<VideoResponse> videoResponses;

    private UserResponse userResponse;
}