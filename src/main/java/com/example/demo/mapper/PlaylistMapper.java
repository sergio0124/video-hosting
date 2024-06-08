package com.example.demo.mapper;

import com.example.demo.domain.PlaylistCreateRequest;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.entity.PlaylistEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistEntity toEntity(PlaylistCreateRequest request);

    @Mapping(target = "videoCount", expression = "java(entity.getVideos().size())")
    PlaylistResponse toDto(PlaylistEntity entity);
}
