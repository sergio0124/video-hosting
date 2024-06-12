package com.example.demo.mapper;

import com.example.demo.domain.VisitResponse;
import com.example.demo.domain.entity.VisitEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public abstract class VisitMapper {

    @Autowired
    public VideoMapper videoMapper;
    static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Mapping(target = "video", expression = "java(videoMapper.toResponse(visit.getVideo()))")
    @Mapping(target = "time", expression = "java(dateFormatter.format(visit.getCreationDate().toLocalDateTime()))")
    public abstract VisitResponse toDto(VisitEntity visit);
}
