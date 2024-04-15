package com.example.demo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@Schema(name = "Запрос на получение комментариев")
public class CommentGetRequest {

    @NotBlank
    @Schema(name = "id видеозаписи",
            required = true,
            example = "04fc55ae-e19c-4ccc-a89d-dee24ccc3d6d")
    private UUID videoId;
}
