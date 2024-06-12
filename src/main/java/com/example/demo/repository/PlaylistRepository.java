package com.example.demo.repository;

import com.example.demo.domain.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, UUID> {

    List<PlaylistEntity> findPlaylistEntitiesByIsPublicAndNameContainsIgnoreCaseOrIsPublicAndDescriptionContainsIgnoreCase(
            Boolean isPublic, String name, Boolean isPublic2, String description);

    List<PlaylistEntity> findPlaylistEntitiesByIsPublic(Boolean isPublic);

    List<PlaylistEntity> findPlaylistEntitiesByUserIdAndNameContainsIgnoreCaseAndIsPublic(UUID userId, String name,
            Boolean isPublic);

    List<PlaylistEntity> findPlaylistEntitiesByUserId(UUID userId);

    List<PlaylistEntity> findPlaylistEntitiesByUserIdAndNameContainsIgnoreCaseAndUserIdAndDescriptionContainsIgnoreCase(
            UUID user_id, String name, UUID user_id2, String description);

    List<PlaylistEntity> findDistinctByPermissionsGroupId(UUID permissions_group_id);
}
