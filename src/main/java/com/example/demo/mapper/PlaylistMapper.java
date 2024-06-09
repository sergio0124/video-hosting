package com.example.demo.mapper;

import com.example.demo.domain.PlaylistCreateRequest;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.entity.PlaylistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    PlaylistEntity toEntity(PlaylistCreateRequest request);

    @Mapping(target = "creationDate", expression = "java(dateFormatter.format(entity.getCreationDate().toLocalDateTime()))")
    @Mapping(target = "videoCount", expression = "java(entity.getVideos().size())")
    PlaylistResponse toDto(PlaylistEntity entity);
}
