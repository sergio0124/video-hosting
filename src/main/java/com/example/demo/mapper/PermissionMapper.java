package com.example.demo.mapper;

import com.example.demo.domain.PermissionResponse;
import com.example.demo.domain.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Mapping(target = "playlistName", expression = "java(permissionEntity.getPlaylist().getName())")
    @Mapping(target = "inspireDate", expression = "java(permissionEntity.getIsTemporary() ? dateFormatter.format(permissionEntity.getInspirationDate().toLocalDateTime()) : null)")
    @Mapping(target = "creationDate", expression = "java(dateFormatter.format(permissionEntity.getCreationDate().toLocalDateTime()))")
    @Mapping(target = "username", expression = "java(permissionEntity.getUser() == null ? null : permissionEntity.getUser().getUsername())")
    @Mapping(target = "userGroup", expression = "java(permissionEntity.getGroup() == null ? null : permissionEntity.getGroup().getName())")
    @Mapping(target = "id", expression = "java(permissionEntity.getId())")
    PermissionResponse doMap(PermissionEntity permissionEntity);
}
