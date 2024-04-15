package com.example.demo.service;

import com.example.demo.domain.CommentCreationRequest;
import com.example.demo.domain.CommentGetRequest;
import com.example.demo.domain.CommentListResponse;
import com.example.demo.domain.CommentRemovalRequest;
import com.example.demo.domain.CommentResponse;
import com.example.demo.domain.CommentUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.manager.CommentManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {

    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    private final CommentManager manager;

    public void createComment(UserEntity user, CommentCreationRequest request) {
        manager.createComment(user, request);
    }

    public void deleteComment(CommentRemovalRequest request) {
        manager.deleteComment(request);
    }

    public void updateComment(UserEntity user, CommentUpdateRequest request) {
        var comment = manager.getCommentById(request.getCommentId());
        if (!Objects.equals(comment.getUser().getUsername(), user.getUsername())) {
            return;
        }
        manager.updateComment(request);
    }

    public CommentListResponse getComments(CommentGetRequest request) {
        return CommentListResponse.builder()
                .comments(manager.getComments(request)
                        .stream().map(c -> CommentResponse.builder()
                                .username(c.getUser().getUsername())
                                .creationDate(df.format(c.getCreationDate()))
                                .text(c.getText())
                                .build())
                        .toList())
                .build();
    }
}
