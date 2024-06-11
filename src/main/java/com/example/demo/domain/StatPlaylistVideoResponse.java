package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class StatPlaylistVideoResponse {

    UUID id;

    String name;

    String description;

    Integer visitCount;

    Integer commentCount;
}
