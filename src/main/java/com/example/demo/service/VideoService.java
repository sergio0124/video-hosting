package com.example.demo.service;

import com.example.demo.domain.TimeCodeRequest;
import com.example.demo.domain.VideoCreateRequest;
import com.example.demo.domain.VideoResponse;
import com.example.demo.domain.entity.TimeCodeEntity;
import com.example.demo.domain.entity.VideoEntity;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.TimeCodeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.repository.PlaylistRepository;
import com.example.demo.repository.TimeCodeRepository;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;
    private final VideoMapper videoMapper;
    private final TimeCodeRepository timeCodeRepository;
    private final TimeCodeMapper timeCodeMapper;
    private final PlaylistRepository playlistRepository;
    private final CommentService commentService;
    private final UserMapper userMapper;

    public List<VideoResponse> getVideos(UUID playlistId, String search) {
	return videoRepository.findVideoEntitiesByPlaylistIdAndNameContainsIgnoreCase(playlistId, search).stream()
		.map(videoMapper::toResponse).toList();
    }

    public void deleteVideo(UUID id) {
	videoRepository.deleteById(id);
    }

    public VideoResponse getVideo(UUID videoId) {
	var videoEntity = videoRepository.findById(videoId).orElseThrow();
	var result = videoMapper.toResponse(videoEntity);
	result.setTimeCodes(videoEntity.getTimeCodes().stream().map(timeCodeMapper::toDto).toList());
	result.setComments(commentService.getCommentsByVideo(result.getId()));
	result.setUser(userMapper.doMap(videoEntity.getPlaylist().getUser()));
	return result;
    }

    public void createVideo(VideoCreateRequest request, UUID id) {
	VideoEntity videoEntity = VideoEntity.builder().videoUrl(request.getVideoUrl())
		.playlist(playlistRepository.findById(request.getPlaylistId()).orElseThrow())
		.creationTime(new Timestamp(new Date().getTime())).imageUrl(request.getImageUrl())
		.description(request.getDescription()).name(request.getName()).build();
	videoEntity = videoRepository.save(videoEntity);
	//	validateTimeCodes(videoEntity.getId(), request.getTimeCodes());
    }

    public void validateTimeCodes(UUID videoId, List<TimeCodeRequest> request) {
	timeCodeRepository.deleteTimeCodeEntitiesByVideoId(videoId);

	VideoEntity video = videoRepository.findById(videoId).orElseThrow();
	List<TimeCodeEntity> timeCodeEntities = request.stream()
		.map(c -> TimeCodeEntity.builder().time(c.getTime()).video(video).description(c.getDescription())
			.build()).toList();
	timeCodeRepository.saveAll(timeCodeEntities);
    }

    public List<VideoResponse> getVideosByRecs(String search) {
	return videoRepository.findVideoEntitiesByPlaylist_IsPublicAndNameContainsIgnoreCase(Boolean.TRUE, search)
		.stream().map(videoMapper::toResponse).toList();
    }
}
