package com.example.demo.controller;

import com.example.demo.domain.CommentCreationRequest;
import com.example.demo.domain.CommentGetRequest;
import com.example.demo.domain.CommentListResponse;
import com.example.demo.domain.CommentRemovalRequest;
import com.example.demo.domain.CommentUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@Validated
@Tag(name = "comments", description = "API для работы с комментариями")
public interface CommentController {

    @Operation(summary = "Создать комментарий")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PostMapping("api/v1/comment")
    void createComment(@AuthenticationPrincipal UserEntity user,
                       @RequestBody @Valid CommentCreationRequest request);

    @Operation(summary = "Удаление комментария")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("api/v1/comment")
    void deleteComment(@AuthenticationPrincipal UserEntity user,
                       @RequestBody @Valid CommentRemovalRequest request);

    @Operation(summary = "Редактирование комментария")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping("api/v1/comment")
    void updateComment(@AuthenticationPrincipal UserEntity user,
                       @RequestBody @Valid CommentUpdateRequest request);

    @Operation(summary = "Получение комментариев под видеозаписью")
    @GetMapping("api/v1/comments")
    CommentListResponse getComments(@RequestBody @Valid CommentGetRequest request);
}
