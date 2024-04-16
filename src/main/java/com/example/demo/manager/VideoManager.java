package com.example.demo.manager;

import com.example.demo.domain.VideoResponse;
import com.example.demo.domain.entity.VideoEntity;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class VideoManager {

    private final VideoRepository repository;

    public List<VideoEntity> getVideosByClassId(UUID id) {
        return repository.findVideoEntitiesByVclassId(id);
    }
}
