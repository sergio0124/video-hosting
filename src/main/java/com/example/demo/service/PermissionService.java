package com.example.demo.service;

import com.example.demo.domain.CreatePermissionRequest;
import com.example.demo.domain.PermissionObjectResponse;
import com.example.demo.domain.PermissionResponse;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.entity.PermissionEntity;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.mapper.PlaylistMapper;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.repository.PlaylistRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionMapper permissionMapper;
    private final PermissionRepository permissionRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final PlaylistRepository playlistRepository;
    private final PlaylistMapper playlistMapper;

    public List<PermissionResponse> getPermissionsByCreatorId(UUID id, UUID playlistId, String search) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}
	final String finalSearch = search;
	if (Objects.isNull(playlistId)) {
	    return permissionRepository.findPermissionEntitiesByPlaylistUserId(id).stream()
		    .filter(c -> c.getUser().getUsername().contains(finalSearch) || c.getUser().getFullname()
			    .contains(finalSearch) || c.getGroup().getName().contains(finalSearch))
		    .map(permissionMapper::doMap).toList();
	}
	return permissionRepository.findPermissionEntitiesByPlaylistUserIdAndPlaylistId(id, playlistId).stream()
		.filter(c -> c.getUser().getUsername().contains(finalSearch) || c.getUser().getFullname()
			.contains(finalSearch) || c.getGroup().getName().contains(finalSearch))
		.map(permissionMapper::doMap).toList();
    }

    public List<PermissionObjectResponse> getPermissionsObjects(String type, String search, UUID id) {
	if (type.equals("group")) {
	    return groupRepository.findGroupEntitiesByUserIdAndNameContainsIgnoreCaseOrUserIdAndDescriptionContainsIgnoreCase(
			    id, search, id, search).stream()
		    .map(c -> new PermissionObjectResponse(type, c.getId(), c.getName(), c.getDescription())).toList();
	}
	return userRepository.findUserEntitiesByUsernameContainsIgnoreCaseOrFullnameContainsIgnoreCase(search, search)
		.stream().map(c -> new PermissionObjectResponse(type, c.getId(), c.getUsername(), c.getFullname()))
		.toList();
    }

    public void createPermission(CreatePermissionRequest request) {
	PermissionEntity permissionEntity = new PermissionEntity();
	permissionEntity.setIsTemporary(request.getIsTemporary());
	permissionEntity.setGroup(
		request.getGroupId() == null ? null : groupRepository.findById(request.getGroupId()).orElseThrow());
	permissionEntity.setUser(
		request.getUserId() == null ? null : userRepository.findById(request.getUserId()).orElseThrow());
	permissionEntity.setPlaylist(playlistRepository.findById(request.getPlaylistId()).orElseThrow());
	permissionEntity.setCreationDate(new Timestamp(new Date().getTime()));
	permissionEntity.setInspirationDate(request.getInspirationDate());
	permissionRepository.save(permissionEntity);
    }

    public List<PlaylistResponse> getPlaylistBySearch(String search, UUID id) {
	return playlistRepository.findPlaylistEntitiesByUserIdAndNameContainsIgnoreCaseAndIsPublic(id, search,
		Boolean.FALSE).stream().map(playlistMapper::toDto).toList();
    }
}
