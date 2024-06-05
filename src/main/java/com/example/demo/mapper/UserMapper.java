package com.example.demo.mapper;

import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "blocked", expression = "java(!user.isAccountNonLocked())")
    UserResponse doMap(UserEntity user);

    UserEntity toEntityFronCreateRequest(UserCreateRequest request);
}
