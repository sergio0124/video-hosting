package com.example.demo.repository;

import com.example.demo.domain.entity.ClassEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, UUID> {

    List<ClassEntity> findClassEntitiesByName(String name, Pageable pageable);

    List<ClassEntity> findClassEntitiesByUserUsernameContains(String teacherName, Pageable pageable);

    List<ClassEntity> findClassEntitiesBySubscribesUserIdAndSubscribesIsAccepted(UUID userId,
                                                                                 Boolean isAccepted,
                                                                                 Pageable pageable);
}
