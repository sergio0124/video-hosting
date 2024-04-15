package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@Schema(name = "Описание курса")
public class ClassResponse {

    @Schema(name = "id курса")
    private UUID id;

    @Schema(name = "название курса")
    private String name;

    @Schema(name = "дата создания курса")
    @JsonProperty("creation_date")
    private String creationDate;

    @Schema(name = "описание курса")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @Schema(name = "автор курса")
    private UserResponse user;
}
