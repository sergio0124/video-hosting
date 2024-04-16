package com.example.demo.service;

import com.example.demo.domain.VideoResponse;
import com.example.demo.manager.VideoManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoManager manager;

    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

    public List<VideoResponse> getVideosByClassId(UUID id) {
        return manager.getVideosByClassId(id).stream()
                .map(c -> VideoResponse.builder()
                        .id(c.getId())
                        .description(c.getDescription())
                        .name(c.getName())
                        .creationDate(df.format(c.getCreationTime()))
                        .build())
                .toList();
    }
}
