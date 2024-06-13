package com.example.demo.repository;

import com.example.demo.domain.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    List<CommentEntity> findCommentEntitiesByCommentId(UUID commentId);

    List<CommentEntity> findCommentEntitiesByVideoId(UUID videoId);

    @Modifying
    @Query("delete from CommentEntity v where v.id=:id")
    void deleteById(@Param("id") UUID id);
}
