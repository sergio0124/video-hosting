package com.example.demo.controller;

import com.example.demo.domain.CommentCreateRequest;
import com.example.demo.domain.CommentResponse;
import com.example.demo.domain.CommentUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/app/comment")
    public CommentResponse createComment(@AuthenticationPrincipal UserEntity user,
	    @RequestBody CommentCreateRequest request) {
	return commentService.createComment(request, user.getId());
    }

    @PutMapping("/app/comment")
    public CommentResponse updateComment(@RequestBody CommentUpdateRequest request) {
	return commentService.updateComment(request);
    }

    @GetMapping("/app/comments/video/{videoId}")
    public List<CommentResponse> getCommentsByVideo(@PathVariable UUID videoId) {
	return commentService.getCommentsByVideo(videoId);
    }}
