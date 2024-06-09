package com.example.demo.service;

import com.example.demo.domain.TimeCodeRequest;
import com.example.demo.domain.entity.TimeCodeEntity;
import com.example.demo.domain.entity.VideoEntity;
import com.example.demo.mapper.TimeCodeMapper;
import com.example.demo.repository.TimeCodeRepository;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TimeCodeService {

    private final TimeCodeRepository timeCodeRepository;
    private final VideoRepository videoRepository;
    private final TimeCodeMapper timeCodeMapper;

    public void saveTimeCode(UUID videoId, TimeCodeRequest request) {
	VideoEntity video = videoRepository.findById(videoId).orElseThrow();
	TimeCodeEntity timeCodeEntity = timeCodeMapper.toEntity(request);
	timeCodeEntity.setVideo(video);
	timeCodeRepository.save(timeCodeEntity);
    }
}
