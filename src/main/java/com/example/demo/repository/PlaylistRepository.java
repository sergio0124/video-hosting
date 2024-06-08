package com.example.demo.repository;

import com.example.demo.domain.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, UUID> {

    List<PlaylistEntity> findPlaylistEntitiesByIsPublicAndNameContainsIgnoreCase(Boolean isPublic, String name);

    List<PlaylistEntity> findPlaylistEntitiesByUserIdAndNameContainsIgnoreCaseAndIsPublic(UUID user_id, String name,
            Boolean isPublic);
}
