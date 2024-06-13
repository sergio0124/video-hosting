package com.example.demo.repository;

import com.example.demo.domain.entity.VideoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {

    List<VideoEntity> findVideoEntitiesByPlaylistIdAndNameContainsIgnoreCase(UUID playlistId, String name);

    List<VideoEntity> findVideoEntitiesByPlaylist_IsPublicAndNameContainsIgnoreCaseOrPlaylist_IsPublicAndDescriptionContainsIgnoreCase(
            Boolean playlist_isPublic, String name, Boolean playlist_isPublic2, String description, Sort sort);

    @Modifying
    @Query("delete from VideoEntity v where v.id=:id")
    void deleteById(@Param("id") UUID id);

    VideoEntity findVideoEntityByVideoUrl(String videoUrl);

    VideoEntity findVideoEntityByImageUrl(String imageUrl);
}
