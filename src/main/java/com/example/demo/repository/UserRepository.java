package com.example.demo.repository;

import com.example.demo.domain.entity.UserEntity;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
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

    UserEntity findUserEntityByCommentsId(UUID comments_id);
}
