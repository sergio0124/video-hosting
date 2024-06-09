package com.example.demo.controller;

import com.example.demo.domain.VideoCreateRequest;
import com.example.demo.domain.VideoResponse;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.VideoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @GetMapping("/app/videos")
    public List<VideoResponse> getVideos(@RequestParam(name = "playlistId") UUID playlistId,
	    @RequestParam(name = "search", required = false) String search) {
	return videoService.getVideos(playlistId, search);
    }

    @DeleteMapping("/app/video/{id}")
    public void deleteVideo(@PathVariable UUID id) {
	videoService.deleteVideo(id);
    }

    @GetMapping("/app/videos/{id}")
    public VideoResponse getVideo(@PathVariable(name = "id") UUID videoId) {
	return videoService.getVideo(videoId);
    }

    @PostMapping("/app/video")
    public void createVideo(@RequestBody @Valid VideoCreateRequest request,
	    @AuthenticationPrincipal UserEntity user) {
	videoService.createVideo(request, user.getId());
    }

    @GetMapping("/app/video/recomedations")
    public List<VideoResponse> getRecommendedVideos(@RequestParam(required = false) String search){
	return videoService.getVideosByRecs(search);
    }
}
