package com.example.demo.repository;

import com.example.demo.domain.entity.VisitEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VisitRepository extends JpaRepository<VisitEntity, UUID> {

    Optional<VisitEntity> findVisitEntityByUserIdAndVideoId(UUID user_id, UUID video_id);

    List<VisitEntity> findVisitEntitiesByUserId(UUID user_id, Sort sort);
}
