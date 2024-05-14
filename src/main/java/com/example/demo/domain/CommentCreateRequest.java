package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateRequest {

    private String text;

    private UUID videoId;

    private UUID userId;

    private UUID commentId;
}
