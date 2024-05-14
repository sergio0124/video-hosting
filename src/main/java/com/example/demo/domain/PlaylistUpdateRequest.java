package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistUpdateRequest {

    private UUID id;

    private String name;

    private String description;

    private Boolean isPublic;
}
