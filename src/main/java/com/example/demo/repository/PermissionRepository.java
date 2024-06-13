package com.example.demo.repository;

import com.example.demo.domain.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, UUID> {

    List<PermissionEntity> findPermissionEntitiesByPlaylistUserId(UUID playlist_user_id);

    List<PermissionEntity> findPermissionEntitiesByPlaylistId(UUID playlist_id);

    @Modifying
    @Query("delete from PermissionEntity v where v.id=:id")
    void deleteById(@Param("id") UUID id);
}
