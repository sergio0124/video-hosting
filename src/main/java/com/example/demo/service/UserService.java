package com.example.demo.service;

import com.example.demo.domain.UserRegisterRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.domain.entity.enums.Role;
import com.example.demo.manager.UserManager;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final PasswordEncoder bCryptPasswordEncoder;
    private final UserManager manager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        var user = manager.findUserByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void createUser(UserRegisterRequest request) {
        UserEntity user = UserEntity
                .builder()
                .status(request.getStatus())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .fullname(request.getFullname())
                .username(request.getUsername())
                .build();

        if (manager.findUserByUsername(request.getUsername()) != null) {
            log.warn("user with login '{}' is already exists", request.getUsername());
            throw new KeyAlreadyExistsException("Пользователь с логином " + request.getUsername() + " уже существует");
        }

        manager.saveUser(user);
    }

    public List<UserResponse> searchUsers(String searchString, Integer page, Integer limit) {
        return manager.searchUsers(searchString, PageRequest.of(
                page,
                limit,
                Sort.by("username").descending())).stream()
                .map(c -> UserResponse
                        .builder()
                        .username(c.getUsername())
                        .role(c.getRole())
                        .status(c.getStatus())
                        .fullname(c.getFullname())
                        .build()).toList();
    }

    public void deleteUser(String username) {
        if (manager.findUserByUsername(username) == null) {
            log.warn("user with username '{}' do not exist", username);
            throw new UsernameNotFoundException("Пользователь с логином " + username + " не существует");
        }
        manager.deleteUser(username);
    }

    public void updateUser(UserEntity user, UserUpdateRequest request) {
        if (!Objects.equals(user.getUsername(), request.getUsername()) && user.getRole() != Role.ADMIN){
            return;
        }
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        manager.updateUser(request);
    }

    public UserResponse getUserInformation(String username) {
        var user = manager.findUserByUsername(username);
        return UserResponse.builder()
                .id(user.getId())
                .fullname(user.getFullname())
                .status(user.getStatus())
                .role(user.getRole())
                .username(user.getUsername())
                .build();
    }
}
