package com.example.demo.controller;

import com.example.demo.domain.GroupCreateRequest;
import com.example.demo.domain.GroupResponse;
import com.example.demo.domain.GroupUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/app/group")
    public void createGroup(@RequestBody GroupCreateRequest request, @AuthenticationPrincipal UserEntity user) {
	groupService.createGroup(request, user);
    }

    @PutMapping("/app/group")
    public void updateGroup(@RequestBody GroupUpdateRequest request, @AuthenticationPrincipal UserEntity user) {
	groupService.updateGroup(request);
    }

    @DeleteMapping("/app/group")
    public void deleteGroup(@RequestParam UUID id) {
	groupService.deleteGroup(id);
    }

    @GetMapping("/app/groups")
    public List<GroupResponse> getGroups(@RequestParam(required = false) String search) {
	return groupService.getGroups(search);
    }

    @GetMapping("/app/created_groups")
    public List<GroupResponse> getCreatedGroups(@RequestParam(required = false) String search,
	    @AuthenticationPrincipal UserEntity user) {
	return groupService.getCreatedGroups(search, user.getId());
    }

    @GetMapping("/app/user/groups")
    public List<GroupResponse> getGroupsByUser(@RequestParam(required = false) String search, @AuthenticationPrincipal UserEntity user) {
	return groupService.getGroupsByUser(search, user.getId());
    }

    @GetMapping("/app/group")
    public GroupResponse getGroup(@RequestParam UUID id) {
	return groupService.getGroup(id);
    }
}
