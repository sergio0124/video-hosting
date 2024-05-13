package com.example.demo.mapper;

import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse doMap(UserEntity user);

    UserEntity toEntityFronCreateRequest(UserCreateRequest request);
}
