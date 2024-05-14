package com.example.demo.mapper;

import com.example.demo.domain.GroupCreateRequest;
import com.example.demo.domain.GroupResponse;
import com.example.demo.domain.GroupUpdateRequest;
import com.example.demo.domain.entity.GroupEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupEntity toEntityFromCreateRequest(GroupCreateRequest request);

    GroupEntity toEntityFromUpdateRequest(GroupUpdateRequest request);

    GroupResponse toResponse(GroupEntity groupEntity);
}
