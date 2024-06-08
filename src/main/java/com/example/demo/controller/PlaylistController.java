package com.example.demo.controller;

import com.example.demo.domain.PlaylistCreateRequest;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.PlaylistUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.repository.PlaylistRepository;
import com.example.demo.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PlaylistController {

    private final PlaylistService playlistService;

    @PostMapping("/app/playlist")
    public void createPlaylist(@RequestBody PlaylistCreateRequest request,
	    @AuthenticationPrincipal UserEntity user){
	playlistService.createPlaylist(request, user.getId());
    }

    @PutMapping("/app/playlist")
    public void updatePlaylist(@RequestBody PlaylistUpdateRequest request){
	playlistService.updatePlaylist(request);
    }

    @DeleteMapping("/app/playlist")
    public void deletePlaylist(@RequestParam UUID id){
	playlistService.deletePlaylist(id);
    }

    @GetMapping("/app/playlists")
    public List<PlaylistResponse> getPlaylists(@RequestParam Boolean isPublic,
	    @RequestParam String search) {
	return playlistService.getPlaylists(search, isPublic);
    }

    @GetMapping("/app/playlists/{id}")
    public PlaylistResponse getPlaylist(@PathVariable UUID id) {
	return playlistService.getPlaylist(id);
    }

    @GetMapping("/app/creator/playlists")
    public List<PlaylistResponse> getPlaylistsByUser(@AuthenticationPrincipal UserEntity user,
	    @RequestParam(name = "search", required = false) String search) {
	return playlistService.getPlaylistsByCreator(user.getId(), search);
    }
}
