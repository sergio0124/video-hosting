package com.example.demo.service;

import com.example.demo.domain.GroupCreateRequest;
import com.example.demo.domain.GroupResponse;
import com.example.demo.domain.GroupUpdateRequest;
import com.example.demo.domain.entity.GroupEntity;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.GroupMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final GroupMapper groupMapper;

    public void createGroup(GroupCreateRequest request, UserEntity auth) {
	GroupEntity groupEntity = groupMapper.toEntityFromCreateRequest(request);
	groupEntity.setUsers(new ArrayList<>());
	groupEntity.setCreationDate(new Timestamp(new Date().getTime()));
	groupEntity.setUser(userRepository.findById(auth.getId()).orElseThrow());

	request.getUsers().forEach(c -> groupEntity.getUsers().add(userRepository.findById(c).orElseThrow()));
	groupRepository.save(groupEntity);
    }

    public void updateGroup(GroupUpdateRequest request) {
	GroupEntity groupEntity = groupRepository.findById(request.getId()).orElseThrow();
	groupEntity.setDescription(request.getDescription());
	groupEntity.setName(request.getName());
	groupEntity.setUsers(new ArrayList<>());
	groupEntity.setCreationDate(new Timestamp(new Date().getTime()));

	request.getUsers().forEach(c -> groupEntity.getUsers().add(userRepository.findById(c).orElseThrow()));
	groupRepository.save(groupEntity);
    }

    public void deleteGroup(UUID id) {
	groupRepository.deleteById(id);
    }

    public List<GroupResponse> getGroups(String search) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}
	List<GroupEntity> groups = groupRepository.findGroupEntitiesByNameContainsIgnoreCase(search);

	return groups.stream()
		.map(c -> GroupResponse.builder().creationDate(c.getCreationDate()).description(c.getDescription())
			.count(c.getUsers().size()).id(c.getId()).name(c.getName()).build()).toList();
    }

    public List<GroupResponse> getGroupsByUser(String search, UUID id) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}
	List<GroupEntity> groups = groupRepository.findGroupEntitiesByUsersIdAndNameContainsIgnoreCase(id, search);

	return groups.stream()
		.map(c -> GroupResponse.builder().creationDate(c.getCreationDate()).description(c.getDescription())
			.count(c.getUsers().size()).id(c.getId()).name(c.getName()).build()).toList();
    }

    public GroupResponse getGroup(UUID id) {
	var group = groupRepository.findById(id).orElseThrow();
	var result = groupMapper.toResponse(group);

	result.setUsers(group.getUsers().stream().map(userMapper::doMap).toList());
	return result;
    }

    public List<GroupResponse> getCreatedGroups(String search, UUID userId) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}
	List<GroupEntity> groups = groupRepository.findGroupEntitiesByUserIdAndNameContainsIgnoreCase(userId, search);

	return groups.stream()
		.map(c -> GroupResponse.builder().creationDate(c.getCreationDate()).description(c.getDescription())
			.count(c.getUsers().size()).id(c.getId()).name(c.getName()).build())
		.collect(Collectors.toList());
    }

    public List<GroupResponse> getGroupsByUserPermissions(UUID id, String search) {
	return groupRepository.findDistinctByUsersIdAndNameContainsIgnoreCase(id, search).stream()
		.map(groupMapper::toResponse).toList();
    }
}
