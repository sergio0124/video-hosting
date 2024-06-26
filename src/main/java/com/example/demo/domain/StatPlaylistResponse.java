package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatPlaylistResponse {

    private Integer videoCount;

    private List<StatPlaylistVideoResponse> videoResponses;

    List<StatPlaylistUserResponse> stats;
}
