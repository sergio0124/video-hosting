package com.example.demo.service;

import com.example.demo.domain.entity.PlaylistEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilsService {

    public Integer getVisitsCount(PlaylistEntity playlist) {
	int result = 0;
	for (var video: playlist.getVideos()
	) {
	    result += video.getVisits().size();
	}
	return result;
    }
}
