package com.example.demo.mapper;

import com.example.demo.domain.PlaylistCreateRequest;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.entity.PlaylistEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistEntity toEntity(PlaylistCreateRequest request);

    PlaylistResponse toDto(PlaylistEntity entity);
}
