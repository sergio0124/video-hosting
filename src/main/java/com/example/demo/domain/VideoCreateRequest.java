package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VideoCreateRequest {

    @NotBlank
    private String name;

    private String imageUrl;

    private String description;

    @NotBlank
    private String videoUrl;

    @NotNull
    private UUID playlistId;
}
