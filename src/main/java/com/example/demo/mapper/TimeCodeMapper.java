package com.example.demo.mapper;

import com.example.demo.domain.TimeCodeRequest;
import com.example.demo.domain.TimeCodeResponse;
import com.example.demo.domain.entity.TimeCodeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeCodeMapper {

    TimeCodeResponse toDto(TimeCodeEntity timeCodeEntity);

    TimeCodeEntity toEntity(TimeCodeRequest timeCodeRequest);
}
