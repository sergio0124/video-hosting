package com.example.demo.repository;

import com.example.demo.domain.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<GroupEntity, UUID> {

    List<GroupEntity> findGroupEntitiesByNameContainsIgnoreCase(String search);

    List<GroupEntity> findGroupEntitiesByUsersIdAndNameContainsIgnoreCase(UUID users_id, String name);

    List<GroupEntity> findGroupEntitiesByUserIdAndNameContainsIgnoreCase(UUID user_id, String name);

    List<GroupEntity> findGroupEntitiesByUserIdAndNameContainsIgnoreCaseOrUserIdAndDescriptionContainsIgnoreCase(
            UUID user_id, String name, UUID user_id2, String description);

    List<GroupEntity> findDistinctByPermissionsPlaylistId(UUID id);

    List<GroupEntity> findDistinctByUsersIdAndNameContainsIgnoreCase(UUID usersId, String name);
}
