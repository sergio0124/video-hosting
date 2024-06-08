package com.example.demo.mapper;

import com.example.demo.domain.PermissionResponse;
import com.example.demo.domain.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "inspireDate", expression = "java(permissionEntity.getIsTemporary() ? null : dateFormatter.format(permissionEntity.getInspirationDate().toLocalDateTime()))")
    @Mapping(target = "creationDate", expression = "java(dateFormatter.format(permissionEntity.getCreationDate().toLocalDateTime()))")
    @Mapping(target = "username", expression = "java(permissionEntity.getUser() == null ? null : permissionEntity.getUser().getUsername())")
    @Mapping(target = "userGroup", expression = "java(permissionEntity.getGroup() == null ? null : permissionEntity.getGroup().getName())")
    PermissionResponse doMap(PermissionEntity permissionEntity);
}
