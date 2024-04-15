package com.example.demo.manager;

import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserManager {

    private final UserRepository repository;

    public UserEntity findUserByUsername(String username) {
        var result = repository.findUserEntityByUsername(username).orElse(null);
        log.info("By username '{}' was found {} user", username, result == null ? 0 : 1);
        return result;
    }

    public void saveUser(UserEntity user) {
        repository.save(user);
        log.info("user with login '{}' is saved", user.getUsername());
    }

    public List<UserEntity> searchUsers(String searchString, Pageable page) {
        var result = repository.findUserEntitiesByUsernameContains(searchString, page);
        log.info("found {} row by search '{}', page {} limit {}",
                result.size(),
                searchString,
                page.getPageNumber(),
                page.getPageSize());
        return result;
    }

    public void deleteUser(String username) {
        var user = repository.findUserEntityByUsername(username).orElseThrow();
        repository.delete(user);
    }

    public void updateUser(UserUpdateRequest request) {
        UserEntity user = UserEntity.builder()
                .status(request.getStatus())
                .id(request.getId())
                .password(request.getPassword())
                .fullname(request.getFullname())
                .username(request.getUsername())
                .build();
        repository.save(user);
    }
}
