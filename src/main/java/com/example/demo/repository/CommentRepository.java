package com.example.demo.repository;

import com.example.demo.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    List<CommentEntity> findCommentEntitiesByCommentId(UUID commentId);

    List<CommentEntity> findCommentEntitiesByVideoId(UUID videoId);
}
