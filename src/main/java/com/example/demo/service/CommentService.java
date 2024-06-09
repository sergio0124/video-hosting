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

import java.sql.Timestamp;
import java.util.Date;
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
	entity.setText(request.getText());
	entity.setUser(userRepository.findById(id).orElseThrow());
	entity.setVideo(videoRepository.findById(request.getVideoId()).orElseThrow());
	entity.setCreationDate(new Timestamp(new Date().getTime()));
	if (Objects.nonNull(request.getCommentId())) {
	    entity.setComment(commentRepository.findById(request.getCommentId()).orElseThrow());
	}

	entity = commentRepository.save(entity);
	var result = commentMapper.toDto(entity);
	result.setUser(userMapper.doMap(entity.getUser()));

	return result;
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

    public List<CommentResponse> getCommentsByVideo(UUID videoId) {
	var comments = commentRepository.findCommentEntitiesByVideoId(videoId).stream()
		.filter(c -> c.getComment() == null)
		.map(c -> CommentResponse.builder().user(userMapper.doMap(c.getUser())).text(c.getText())
			.creationDate(c.getCreationDate().toString()).id(c.getId()).build()).toList();
	comments.forEach(this::addCommentResponse);
	return comments;
    }

    private void addCommentResponse(CommentResponse commentResponse) {
	var childs = commentRepository.findCommentEntitiesByCommentId(commentResponse.getId()).stream()
		.map(commentMapper::toDto).toList();
	commentResponse.setComments(childs);
	commentResponse.getComments().forEach(this::addCommentResponse);
    }
}
