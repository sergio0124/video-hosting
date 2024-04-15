package com.example.demo.controller.implementation;

import com.example.demo.controller.CommentController;
import com.example.demo.domain.CommentCreationRequest;
import com.example.demo.domain.CommentGetRequest;
import com.example.demo.domain.CommentListResponse;
import com.example.demo.domain.CommentRemovalRequest;
import com.example.demo.domain.CommentUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentControllerImpl implements CommentController {

    private final CommentService service;

    @Override
    public void createComment(UserEntity user, CommentCreationRequest request) {
        service.createComment(user, request);
    }

    @Override
    public void deleteComment(UserEntity user, CommentRemovalRequest request) {
        service.deleteComment(request);
    }

    @Override
    public void updateComment(UserEntity user, CommentUpdateRequest request) {
        service.updateComment(user, request);
    }

    @Override
    public CommentListResponse getComments(CommentGetRequest request) {
        return service.getComments(request);
    }

}
