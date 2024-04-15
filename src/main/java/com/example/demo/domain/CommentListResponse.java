package com.example.demo.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(name = "Список комментариев")
public class CommentListResponse {

    @Schema(name = "Список комментариев")
    private List<CommentResponse> comments;
}
