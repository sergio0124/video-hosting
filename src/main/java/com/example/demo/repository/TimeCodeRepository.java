package com.example.demo.repository;

import com.example.demo.domain.entity.TimeCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TimeCodeRepository extends JpaRepository<TimeCodeEntity, UUID> {

    List<TimeCodeEntity> findTimeCodeEntitiesByVideoId(UUID video_id);

    void deleteTimeCodeEntitiesByVideoId(UUID videoId);
}
