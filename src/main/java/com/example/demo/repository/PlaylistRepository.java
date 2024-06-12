package com.example.demo.repository;

import com.example.demo.domain.PlaylistResponse;
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

    List<PlaylistEntity> findPlaylistEntitiesByPermissionsUserIdAndNameContainsIgnoreCase(UUID permissions_user_id,
	    String name);

    List<PlaylistEntity> findPlaylistEntitiesByUserIdAndIsPublicAndNameContainsIgnoreCase(UUID user_id,
	    Boolean isPublic, String name);

    List<PlaylistEntity> findDistinctByPermissionsGroupUsersIdAndNameContainsIgnoreCaseAndUserIdOrPermissionsUserIdAndNameContainsIgnoreCaseAndUserId(
	    UUID permissions_group_users_id, String name, UUID user_id, UUID permissions_user_id, String name2,
	    UUID user_id2);
}
