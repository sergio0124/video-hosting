package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatGroupResponse {

    Integer permissionsCount;

    List<StatGroupUserResponse> stats;

    List<StatGroupPlaylistResponse> playlists;
}
