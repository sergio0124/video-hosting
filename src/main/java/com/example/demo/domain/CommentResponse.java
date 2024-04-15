package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(name = "Тело комментария")
public class CommentResponse {

    @Schema(name = "Текст комментария")
    private String text;

    @Schema(name = "Дата написания комментария")
    @JsonProperty("creation_date")
    private String creationDate;

    @Schema(name = "Логин автора комментария")
    private String username;
}
