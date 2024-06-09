package com.example.demo.service;

import com.example.demo.domain.entity.VisitEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import com.example.demo.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final VisitRepository visitRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public void markVisited(UUID videoId, UUID userId) {
        VisitEntity visitEntity = visitRepository.findVisitEntityByUserIdAndVideoId(userId, videoId).orElse(null);
        if (Objects.isNull(visitEntity)){
            visitEntity = new VisitEntity();
            visitEntity.setUser(userRepository.findById(userId).orElseThrow());
            visitEntity.setVideo(videoRepository.findById(videoId).orElseThrow());
        }
        visitEntity.setCreationDate(new Timestamp(new Date().getTime()));
        visitRepository.save(visitEntity);
    }
}
