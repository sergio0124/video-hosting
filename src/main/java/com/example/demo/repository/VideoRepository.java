package com.example.demo.repository;

import com.example.demo.domain.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {

    List<VideoEntity> findVideoEntitiesByPlaylistIdAndNameContainsIgnoreCase(UUID playlistId, String name);

    List<VideoEntity> findVideoEntitiesByPlaylist_IsPublicAndNameContainsIgnoreCase(Boolean playlist_isPublic,
            String name);
}
