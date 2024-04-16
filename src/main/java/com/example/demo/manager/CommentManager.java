package com.example.demo.manager;

import com.example.demo.domain.CommentCreationRequest;
import com.example.demo.domain.CommentGetRequest;
import com.example.demo.domain.CommentListResponse;
import com.example.demo.domain.CommentRemovalRequest;
import com.example.demo.domain.CommentUpdateRequest;
import com.example.demo.domain.entity.CommentEntity;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommentManager {

    private final CommentRepository repository;
    private final VideoRepository videoRepository;

    public void createComment(UserEntity user, CommentCreationRequest request) {
        var video = videoRepository.findById(request.getVideoId()).orElseThrow();
        CommentEntity commentEntity = CommentEntity.builder()
                .text(request.getText())
                .video(video)
                .user(user)
                .build();
        repository.save(commentEntity);
    }

    public void deleteComment(CommentRemovalRequest request) {
        var comment = repository.findById(request.getCommentId()).orElseThrow();
        repository.delete(comment);
    }

    public CommentEntity getCommentById(UUID commentId) {
        return repository.findById(commentId).orElseThrow();
    }

    public void updateComment(CommentUpdateRequest request) {
        CommentEntity commentEntity = repository.findById(request.getCommentId()).orElseThrow();
        commentEntity.setText(request.getText());
        repository.save(commentEntity);
    }

    public List<CommentEntity> getComments(CommentGetRequest request) {
        return repository.findCommentEntitiesByVideoId(UUID.fromString(request.getVideoId()));
    }
}
