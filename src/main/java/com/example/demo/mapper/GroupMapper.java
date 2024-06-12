package com.example.demo.mapper;

import com.example.demo.domain.GroupCreateRequest;
import com.example.demo.domain.GroupResponse;
import com.example.demo.domain.GroupUpdateRequest;
import com.example.demo.domain.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class GroupMapper {

    @Autowired
    UserMapper userMapper;

    @Mapping(target = "users", ignore = true)
    public abstract GroupEntity toEntityFromCreateRequest(GroupCreateRequest request);

    @Mapping(target = "creator", expression = "java(userMapper.doMap(groupEntity.getUser()))")
    @Mapping(target = "count", expression = "java(groupEntity.getUsers().size())")
    public abstract GroupResponse toResponse(GroupEntity groupEntity);
}
