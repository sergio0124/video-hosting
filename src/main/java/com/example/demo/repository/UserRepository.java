package com.example.demo.repository;

import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByMail(String email);

    List<UserEntity> findUserEntitiesByUsernameContainsIgnoreCaseOrFullnameContainsIgnoreCase(String username,
            String fullname);

    List<UserEntity> findUserEntitiesByGroupUsersId(UUID id);

    Optional<UserEntity> findUserEntityByUsername(String username);

    UserEntity findUserEntityByCommentsId(UUID commentsId);

    List<UserEntity> findDistinctByPermissionsPlaylistId(UUID playlistId);

    List<UserEntity> findUserEntitiesByRoleAndFullnameContainsIgnoreCaseOrRoleAndUsernameContainsIgnoreCase(Role role,
            String fullname, Role role2, String username);

    @Modifying
    @Query("delete from UserEntity v where v.id=:id")
    void deleteById(@Param("id") UUID id);
}
