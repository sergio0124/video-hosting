package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoResponse {

    private UUID id;

    private String name;

    private String imageUrl;

    private String description;

    private String videoUrl;

    private String creationTime;

    private Integer count;

    private List<TimeCodeResponse> timeCodes;

    private List<CommentResponse> comments;

    private UserResponse user;
}
