package com.example.demo.mapper;

import com.example.demo.domain.CommentCreateRequest;
import com.example.demo.domain.CommentResponse;
import com.example.demo.domain.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentEntity toEntity(CommentCreateRequest request);

    CommentResponse toDto(CommentEntity comment);
}
