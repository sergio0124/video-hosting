package com.example.demo.service;

import com.example.demo.domain.PermissionCreateRequest;
import com.example.demo.domain.PermissionObjectResponse;
import com.example.demo.domain.PermissionResponse;
import com.example.demo.domain.PermissionUpdateRequest;
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
import java.text.SimpleDateFormat;
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
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<PermissionResponse> getPermissionsByCreatorId(UUID id, UUID playlistId, String search) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}
	final String finalSearch = search;
	if (Objects.isNull(playlistId)) {
	    return permissionRepository.findPermissionEntitiesByPlaylistUserId(id).stream()
		    .filter(c -> (Objects.isNull(c.getUser())
			    ? Boolean.FALSE
			    : c.getUser().getUsername().toUpperCase().contains(finalSearch.toUpperCase()) || c.getUser()
				    .getFullname().toUpperCase()
				    .contains(finalSearch.toUpperCase())) || (Objects.isNull(c.getGroup())
			    ? Boolean.FALSE
			    : c.getGroup().getName().toUpperCase().contains(finalSearch.toUpperCase())))
		    .map(permissionMapper::doMap).toList();
	}
	return permissionRepository.findPermissionEntitiesByPlaylistId(playlistId).stream()
		.filter(c -> (Objects.isNull(c.getUser())
			? Boolean.FALSE
			: c.getUser().getUsername().toUpperCase().contains(finalSearch.toUpperCase()) || c.getUser()
				.getFullname().toUpperCase().contains(finalSearch.toUpperCase())) || (Objects.isNull(
			c.getGroup())
			? Boolean.FALSE
			: c.getGroup().getName().toUpperCase().contains(finalSearch.toUpperCase())))
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

    public void createPermission(PermissionCreateRequest request) {
	PermissionEntity permissionEntity = new PermissionEntity();
	permissionEntity.setIsTemporary(Objects.isNull(request.getIsTemporary())
		? Objects.nonNull(request.getInspirationDate())
		: request.getIsTemporary());
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

    public void deletePermission(UUID id) {
	permissionRepository.deleteById(id);
    }

    public PermissionResponse getPermission(UUID id) {
	return  permissionMapper.doMap(permissionRepository.findById(id).orElseThrow());
    }

    public void updatePermission(PermissionUpdateRequest request) {
	PermissionEntity permissionEntity = permissionRepository.findById(request.getId()).orElseThrow();
	permissionEntity.setDescription(request.getDescription());
	permissionEntity.setInspirationDate(request.getInspirationDate());
	permissionEntity.setIsTemporary(request.getIsTemporary());
	permissionRepository.save(permissionEntity);
    }
}
