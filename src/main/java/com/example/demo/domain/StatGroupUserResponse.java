package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatGroupUserResponse {

    UUID userId;

    String username;

    String playlistName;

    String videoName;

    Boolean isWatched;
}
