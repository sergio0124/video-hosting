package com.example.demo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(name = "Шаблон списка курсов")
public class ClassListResponse {

    @Schema(name = "Список курсов")
    private List<ClassResponse> classes;
}
