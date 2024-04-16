package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class VideoResponse {
    private UUID id;
    private String name;
    private String description;
    @JsonProperty("creation_date")
    private String creationDate;
}
