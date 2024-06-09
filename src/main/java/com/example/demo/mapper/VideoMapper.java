package com.example.demo.mapper;

import com.example.demo.domain.VideoResponse;
import com.example.demo.domain.entity.VideoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "creationTime", expression = "java(dateFormatter.format(video.getCreationTime().toLocalDateTime()))")
    @Mapping(target = "count", expression = "java(video.getVisits().size())")
    VideoResponse toResponse(VideoEntity video);
}
