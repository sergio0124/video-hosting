package com.example.demo.service;

import com.example.demo.domain.CommentCreateRequest;
import com.example.demo.domain.CommentResponse;
import com.example.demo.domain.CommentUpdateRequest;
import com.example.demo.domain.entity.CommentEntity;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    private final CommentMapper commentMapper;
    private final UserMapper userMapper;

    public CommentResponse createComment(CommentCreateRequest request, UUID id) {
        CommentEntity entity = commentMapper.toEntity(request);
        entity.setUser(userRepository.findById(id).orElseThrow());
        entity.setVideo(videoRepository.findById(request.getVideoId()).orElseThrow());

        entity = commentRepository.save(entity);
        var result = commentMapper.toDto(entity);
        result.setUser(userMapper.doMap(entity.getUser()));

        return result;
    }

    private List<CommentResponse> getInnerComments(UUID commentId) {
        var comments = commentRepository.findCommentEntitiesByCommentId(commentId);
        return comments.stream().map(c -> {
            CommentResponse response = commentMapper.toDto(c);
            response.setUser(userMapper.doMap(c.getUser()));
            response.setComments(getInnerComments(response.getId()));
            return response;
        }).collect(Collectors.toList());
    }

    private CommentResponse toResponse(CommentEntity commentEntity) {
        CommentResponse commentResponse = commentMapper.toDto(commentEntity);
        commentResponse.setUser(userMapper.doMap(commentEntity.getUser()));
        return commentResponse;
    }

    public CommentResponse updateComment(CommentUpdateRequest request) {
        CommentEntity commentEntity = commentRepository.findById(request.getId()).orElseThrow();
        commentEntity.setText(request.getText());
        return toResponse(commentRepository.save(commentEntity));
    }
}
