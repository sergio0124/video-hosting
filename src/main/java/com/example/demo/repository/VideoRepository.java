package com.example.demo.repository;

import com.example.demo.domain.entity.CommentEntity;
import com.example.demo.domain.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {
}
