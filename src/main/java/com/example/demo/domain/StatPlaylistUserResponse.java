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
public class StatPlaylistUserResponse {

    UUID userId;

    String username;

    String groupName;

    String videoName;

    Boolean isWatched;
}
