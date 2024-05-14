package com.example.demo.mapper;

import com.example.demo.domain.VideoResponse;
import com.example.demo.domain.entity.VideoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoResponse toResponse(VideoEntity video);
}
