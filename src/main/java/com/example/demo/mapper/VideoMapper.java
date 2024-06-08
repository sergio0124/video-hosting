package com.example.demo.mapper;

import com.example.demo.domain.VideoResponse;
import com.example.demo.domain.entity.VideoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    @Mapping(target = "count", expression = "java(video.getVisits().size())")
    VideoResponse toResponse(VideoEntity video);
}
