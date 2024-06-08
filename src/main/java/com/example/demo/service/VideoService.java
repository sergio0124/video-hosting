package com.example.demo.service;

import com.example.demo.domain.VideoResponse;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;

    public List<VideoResponse> getVideos(UUID playlistId, String search) {
	return videoRepository.findVideoEntitiesByPlaylistIdAndNameContainsIgnoreCase(playlistId, search).stream()
		.map(videoMapper::toResponse).toList();
    }

    public void deleteVideo(UUID id) {
	videoRepository.deleteById(id);
    }
}
