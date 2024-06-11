package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class StatGroupPlaylistResponse {

    UUID playlistId;

    String name;

    String description;

    String permissionDescription;
}
