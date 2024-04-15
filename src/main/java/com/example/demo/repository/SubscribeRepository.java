package com.example.demo.repository;

import com.example.demo.domain.entity.SubscribeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubscribeRepository extends JpaRepository<SubscribeEntity, UUID> {

    Optional<SubscribeEntity> findSubscribeEntityByvclassIdAndUserUsername(UUID classId, String username);

    List<SubscribeEntity> findSubscribeEntitiesByIsAcceptedEqualsAndUserUsername(Boolean isAccepted, String username, Pageable pageable);
}
