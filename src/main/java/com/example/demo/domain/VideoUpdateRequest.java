package com.example.demo.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoUpdateRequest {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    private String description;

    private String imageUrl;

    private String videoUrl;
}
