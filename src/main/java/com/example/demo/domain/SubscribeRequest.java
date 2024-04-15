package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Schema(name = "Запрос на подписку пользователя")
public class SubscribeRequest {

    @NotBlank
    @Schema(name = "id курса лекций",
            required = true,
            example = "04fc55ae-e19c-4ccc-a89d-dee24ccc3d6d")
    @JsonProperty("class_id")
    private UUID classId;
}
