package com.example.demo.service;

import com.example.demo.domain.StatGroupPlaylistResponse;
import com.example.demo.domain.StatGroupResponse;
import com.example.demo.domain.StatGroupUserResponse;
import com.example.demo.domain.StatPlaylistResponse;
import com.example.demo.domain.StatPlaylistUserResponse;
import com.example.demo.domain.StatPlaylistVideoResponse;
import com.example.demo.domain.entity.GroupEntity;
import com.example.demo.domain.entity.PlaylistEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.PlaylistRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatService {

    private final PlaylistRepository playlistRepository;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserMapper userMapper;

    public StatPlaylistResponse getPlaylistStatistics(UUID id) {
	var result = new StatPlaylistResponse();

	var playlist = playlistRepository.findById(id).orElseThrow();
	result.setVideoCount(playlist.getVideos().size());

	result.setVideoResponses(getVideoResponses(playlist));

	result.setStats(getPlaylistStats(playlist));
	return result;
    }

    private List<StatPlaylistUserResponse> getPlaylistStats(PlaylistEntity playlist) {
	List<StatPlaylistUserResponse> users = userRepository.findDistinctByPermissionsPlaylistId(playlist.getId())
		.stream().map(c -> new StatPlaylistUserResponse(c.getId(), c.getUsername(), "-", null, null))
		.collect(Collectors.toList());

	for (var group : groupRepository.findDistinctByPermissionsPlaylistId(playlist.getId())) {
	    for (var user : group.getUsers()) {
		users.add(new StatPlaylistUserResponse(user.getId(), user.getUsername(), group.getName(), null, null));
	    }
	}

	users = users.stream().filter(distinctByKey(StatPlaylistUserResponse::getUserId)).collect(Collectors.toList());

	List<StatPlaylistUserResponse> result = new ArrayList<>();
	for (var userStat : users) {
	    for (var video : playlist.getVideos()) {
		result.add(new StatPlaylistUserResponse(userStat.getUserId(), userStat.getUsername(),
			userStat.getGroupName(), video.getName(),
			!video.getVisits().stream().filter(c -> c.getUser().getId() == userStat.getUserId()).toList()
				.isEmpty()));
	    }
	}

	return result;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
	Map<Object, Boolean> seen = new ConcurrentHashMap<>();
	return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private List<StatPlaylistVideoResponse> getVideoResponses(PlaylistEntity playlist) {
	return playlist.getVideos().stream().map(c -> {
	    var response = new StatPlaylistVideoResponse();
	    response.setId(c.getId());
	    response.setName(c.getName());
	    response.setDescription(c.getDescription());
	    response.setCommentCount(c.getComments().size());
	    response.setVisitCount(c.getVisits().size());
	    return response;
	}).toList();
    }

    public StatGroupResponse getGroupStatistics(UUID id) {
	var result = new StatGroupResponse();

	var group = groupRepository.findById(id).orElseThrow();
	result.setPermissionsCount(group.getPermissions().size());

	result.setStats(getGroupStats(group));

	result.setPlaylists(getGroupPlaylists(group));

	return result;
    }

    private List<StatGroupPlaylistResponse> getGroupPlaylists(GroupEntity group) {
	List<StatGroupPlaylistResponse> result = new ArrayList<>();
	for (var permission : group.getPermissions()) {
	    var playlist = permission.getPlaylist();
	    result.add(new StatGroupPlaylistResponse(playlist.getId(), playlist.getName(), playlist.getDescription(),
		    permission.getDescription()));
	}
	return result;
    }

    private List<StatGroupUserResponse> getGroupStats(GroupEntity group) {
	List<StatGroupUserResponse> users = group.getUsers().stream()
		.map(c -> new StatGroupUserResponse(c.getId(), c.getUsername(), null, null, null)).toList();

	List<StatGroupUserResponse> result = new ArrayList<>();
	for (var user : users) {
	    for (var permission : group.getPermissions()) {
		var playlist = permission.getPlaylist();
		for (var video : playlist.getVideos()) {
		    result.add(new StatGroupUserResponse(user.getUserId(), user.getUsername(), playlist.getName(),
			    video.getName(),
			    !video.getVisits().stream().filter(c -> c.getUser().getId() == user.getUserId()).toList()
				    .isEmpty()));
		}
	    }
	}

	return result;
    }
}
