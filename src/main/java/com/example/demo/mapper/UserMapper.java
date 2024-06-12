package com.example.demo.mapper;

import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface UserMapper {
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "blocked", expression = "java(!user.isAccountNonLocked())")
    @Mapping(target = "registrationDate", expression = "java(dateFormatter.format(user.getRegistrationDate().toLocalDateTime()))")
    UserResponse doMap(UserEntity user);

    UserEntity toEntityFronCreateRequest(UserCreateRequest request);
}
