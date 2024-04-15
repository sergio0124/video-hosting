package com.example.demo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Schema(name = "Запрос на создание комментария")
public class CommentCreationRequest {

    @NotBlank
    @Schema(description = "содержание комментария",
            example = "Any string",
            required = true)
    private String text;

    @NotBlank
    @Schema(description = "id видеозаписи",
            example = "84939a6e-6737-4885-8c31-03c01f83636c",
            required = true)
    private UUID videoId;
}
