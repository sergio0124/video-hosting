package com.example.demo.controller;

import com.example.demo.domain.StatGroupResponse;
import com.example.demo.domain.StatPlaylistResponse;
import com.example.demo.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class StatisticsController {

    private final StatService statService;

    @GetMapping("/app/stat/playlist/{id}")
    public StatPlaylistResponse getPlaylistStatistics(@PathVariable UUID id) {
        return statService.getPlaylistStatistics(id);
    }

    @GetMapping("/app/stat/group/{id}")
    public StatGroupResponse getGroupStatistics(@PathVariable UUID id) {
        return statService.getGroupStatistics(id);
    }
}
