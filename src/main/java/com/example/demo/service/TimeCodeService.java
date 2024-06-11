package com.example.demo.service;

import com.example.demo.domain.TimeCodeRequest;
import com.example.demo.domain.TimeCodeResponse;
import com.example.demo.domain.entity.TimeCodeEntity;
import com.example.demo.domain.entity.VideoEntity;
import com.example.demo.mapper.TimeCodeMapper;
import com.example.demo.repository.TimeCodeRepository;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void createTimeCode(TimeCodeRequest timeCodeRequest) {
	TimeCodeEntity timeCodeEntity = new TimeCodeEntity();
	timeCodeEntity.setTime(timeCodeRequest.getTime());
	timeCodeEntity.setDescription(timeCodeRequest.getDescription());
	timeCodeEntity.setVideo(videoRepository.findById(timeCodeRequest.getVideoId()).orElseThrow());
	timeCodeRepository.save(timeCodeEntity);
    }

    public List<TimeCodeResponse> getTimeCodesByVideo(UUID videoId) {
	return timeCodeRepository.findTimeCodeEntitiesByVideoId(videoId).stream()
		.map(c -> TimeCodeResponse.builder().time(c.getTime()).description(c.getDescription()).id(c.getId())
			.build()).toList();
    }

    public void deleteTimeCode(UUID id) {
	timeCodeRepository.deleteById(id);
    }

    public void updateTimeCode(TimeCodeRequest request) {
	TimeCodeEntity timeCodeEntity = timeCodeRepository.findById(request.getId()).orElseThrow();
	timeCodeEntity.setDescription(request.getDescription());
	timeCodeEntity.setTime(request.getTime());
	timeCodeRepository.save(timeCodeEntity);
    }
}
