package com.example.demo.mapper;

import com.example.demo.domain.GroupCreateRequest;
import com.example.demo.domain.GroupResponse;
import com.example.demo.domain.GroupUpdateRequest;
import com.example.demo.domain.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "users", ignore = true)
    GroupEntity toEntityFromCreateRequest(GroupCreateRequest request);

    @Mapping(target = "count", expression = "java(groupEntity.getUsers().size())")
    GroupResponse toResponse(GroupEntity groupEntity);
}
