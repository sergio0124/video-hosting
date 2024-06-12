package com.example.demo.mapper;

import com.example.demo.domain.PlaylistCreateRequest;
import com.example.demo.domain.PlaylistResponse;
import com.example.demo.domain.entity.PlaylistEntity;
import com.example.demo.service.UtilsService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public abstract class PlaylistMapper {

    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    public UtilsService utils;
    @Autowired
    public UserMapper userMapper;

    abstract public PlaylistEntity toEntity(PlaylistCreateRequest request);

    @Mapping(target = "userResponse", expression = "java(userMapper.doMap(entity.getUser()))")
    @Mapping(target = "visitsCount", expression = "java(utils.getVisitsCount(entity))")
    @Mapping(target = "creationDate", expression = "java(dateFormatter.format(entity.getCreationDate().toLocalDateTime()))")
    @Mapping(target = "videoCount", expression = "java(entity.getVideos().size())")
    abstract public PlaylistResponse toDto(PlaylistEntity entity);
}
