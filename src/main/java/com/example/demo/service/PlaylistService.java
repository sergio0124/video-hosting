package com.example.demo.service;

import com.example.demo.domain.PlaylistCreateRequest;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.PlaylistUpdateRequest;
import com.example.demo.domain.entity.PlaylistEntity;
import com.example.demo.mapper.PlaylistMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.VideoMapper;
import com.example.demo.repository.PlaylistRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaylistService {

    private final PlaylistRepository playlistRepository;

    private final UserRepository userRepository;

    private final PlaylistMapper playlistMapper;

    private final VideoMapper videoMapper;

    private final UserMapper userMapper;

    public void createPlaylist(PlaylistCreateRequest request, UUID userId) {
	var entity = playlistMapper.toEntity(request);
	entity.setCreationDate(new Timestamp(new Date().getTime()));
	entity.setUser(userRepository.findById(userId).orElseThrow());
	playlistRepository.save(entity);
    }

    public void updatePlaylist(PlaylistUpdateRequest request) {
	var entity = playlistRepository.findById(request.getId()).orElseThrow();
	entity.setName(request.getName());
	entity.setDescription(request.getDescription());
	entity.setIsPublic(request.getIsPublic());

	playlistRepository.save(entity);
    }

    public void deletePlaylist(UUID id) {
	playlistRepository.deleteById(id);
    }

    public List<PlaylistResponse> getPlaylists(String search, Boolean isPublic) {
	var playlists = playlistRepository.findPlaylistEntitiesByIsPublicAndNameContainsIgnoreCaseOrIsPublicAndDescriptionContainsIgnoreCase(
		isPublic, search, isPublic, search);
	return playlists.stream().map(c -> {
	    var model = playlistMapper.toDto(c);
	    model.setVideoCount(c.getVideos().size());
	    return model;
	}).toList();
    }

    public PlaylistResponse getPlaylist(UUID id) {
	var playlist = playlistRepository.findById(id).orElseThrow();
	var response = playlistMapper.toDto(playlist);
	response.setVideoResponses(
		playlist.getVideos().stream().map(videoMapper::toResponse).collect(Collectors.toList()));
	response.setUserResponse(userMapper.doMap(playlist.getUser()));
	return response;
    }

    public List<PlaylistResponse> getPlaylistsByCreator(UUID id, String search) {
	List<PlaylistEntity> result;
	if (StringUtils.isBlank(search)) {
	    result = playlistRepository.findPlaylistEntitiesByUserId(id);
	} else {
	    result = playlistRepository.findPlaylistEntitiesByUserIdAndNameContainsIgnoreCaseOrUserIdAndDescriptionContainsIgnoreCase(
		    id, search, id, search);
	}
	return result.stream().map(playlistMapper::toDto).toList();
    }

    public List<PlaylistResponse> getPublicPlaylists(String search) {
	List<PlaylistEntity> result;
	if (StringUtils.isBlank(search)) {
	    result = playlistRepository.findPlaylistEntitiesByIsPublic(Boolean.TRUE);
	} else {
	    result = playlistRepository.findPlaylistEntitiesByIsPublicAndNameContainsIgnoreCaseOrIsPublicAndDescriptionContainsIgnoreCase(
		    Boolean.TRUE, search, Boolean.TRUE, search);
	}
	return result.stream().map(playlistMapper::toDto).toList();
    }

    public List<PlaylistResponse> getGroupPlaylists(UUID id) {
	return playlistRepository.findDistinctByPermissionsGroupId(id).stream().map(playlistMapper::toDto).toList();
    }

    public List<PlaylistResponse> getPlaylistsByPersonalPermission(UUID id, String search) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}
	return playlistRepository.findPlaylistEntitiesByPermissionsUserIdAndNameContainsIgnoreCase(id, search).stream()
		.map(playlistMapper::toDto).toList();
    }

    public List<PlaylistResponse> getPlaylistsByCreatorAndStatus(UUID creatorId, String search, Boolean isPrivate,
	    UUID userId) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}
	if (BooleanUtils.isNotTrue(isPrivate)) {
	    return playlistRepository.findPlaylistEntitiesByUserIdAndIsPublicAndNameContainsIgnoreCase(creatorId,
		    Boolean.TRUE, search).stream().map(playlistMapper::toDto).toList();
	} else {
	    return playlistRepository.findDistinctByPermissionsGroupUsersIdAndNameContainsIgnoreCaseAndUserIdOrPermissionsUserIdAndNameContainsIgnoreCaseAndUserId(
		    userId, search, creatorId, userId, search, creatorId).stream().map(playlistMapper::toDto).toList();
	}
    }
}
