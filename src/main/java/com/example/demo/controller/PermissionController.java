package com.example.demo.controller;

import com.example.demo.domain.PermissionCreateRequest;
import com.example.demo.domain.PermissionObjectResponse;
import com.example.demo.domain.PermissionResponse;
import com.example.demo.domain.PermissionUpdateRequest;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.PermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class PermissionController {

    private final PermissionService permissionService;

    @GetMapping("/app/creator/permissions")
    public List<PermissionResponse> getPermissionsByCreator(@AuthenticationPrincipal UserEntity user,
	    @RequestParam(name = "playlistId", required = false) UUID playlistId,
	    @RequestParam(name ="search", required = false) String search) {
	return permissionService.getPermissionsByCreatorId(user.getId(), playlistId, search);
    }

    @GetMapping("/app/creator/permissions/{id}")
    public PermissionResponse getPermission(@PathVariable UUID id) {
	return permissionService.getPermission(id);
    }

    @PutMapping("/app/creator/permissions")
    public void updatePermission(@RequestBody PermissionUpdateRequest request) {
	permissionService.updatePermission(request);
    }

    @GetMapping("/app/creator/permissions/objects")
    public List<PermissionObjectResponse> getPermissionsObjects(@RequestParam(name = "type") String type,
	    @RequestParam(name = "search") String search, @AuthenticationPrincipal UserEntity user) {
	return permissionService.getPermissionsObjects(type, search, user.getId());
    }

    @PostMapping("/app/creator/permissions")
    public void createPermission(@RequestBody @Valid PermissionCreateRequest request) {
	permissionService.createPermission(request);
    }

    @GetMapping("/app/permissions/playlists")
    public List<PlaylistResponse> getPlaylists(@RequestParam String search, @AuthenticationPrincipal UserEntity user) {
	return permissionService.getPlaylistBySearch(search, user.getId());
    }

    @DeleteMapping("/app/creator/permissions")
    public void deletePlaylist(@RequestParam UUID id) {
	permissionService.deletePermission(id);
    }
}
