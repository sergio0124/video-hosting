package com.example.demo.repository;

import com.example.demo.domain.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

    List<PermissionEntity> findPermissionEntitiesByPlaylistUserId(UUID playlist_user_id);
}
