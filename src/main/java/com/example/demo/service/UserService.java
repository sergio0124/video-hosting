package com.example.demo.service;

import com.example.demo.domain.ProfileUpdateRequest;
import com.example.demo.domain.UserCreateJsonRequest;
import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;

    public void blockUser(UUID userId) {
	var user = userRepository.findById(userId).orElseThrow();
	user.setNonLocked(!user.isAccountNonLocked());
	userRepository.save(user);
    }

    public List<UserResponse> getUsers(String search) {
	if (StringUtils.isBlank(search)) {
	    search = "";
	}

	var users = userRepository.findUserEntitiesByUsernameContainsIgnoreCaseOrFullnameContainsIgnoreCase(search,
		search);

	return users.stream().map(userMapper::doMap).toList();
    }

    public UserResponse createUser(UserCreateRequest request) {
	UserEntity userEntity = userMapper.toEntityFronCreateRequest(request);
	userEntity.setId(UUID.randomUUID());
	userEntity.setRegistrationDate(new Timestamp(new Date().getTime()));

	var password = String.valueOf(UUID.randomUUID());
	userEntity.setPassword(passwordEncoder.encode(password));
	mailSenderService.send(userEntity.getMail(), "Создание аккаунта",
		"Для логина через пользователя " + userEntity.getUsername() + " воспользуйтесь паролем " + password);
	return userMapper.doMap(userRepository.save(userEntity));
    }

    public UserResponse updateUser(UserUpdateRequest request) {
	var userEntity = userRepository.findById(request.getId()).orElseThrow();

	userEntity.setFullname(request.getFullname());
	userEntity.setMail(request.getMail());
	userEntity.setStatus(request.getStatus());
	userEntity.setUsername(request.getUsername());
	userEntity.setRole(request.getRole());
	return userMapper.doMap(userRepository.save(userEntity));
    }

    public void createUsers(UserCreateJsonRequest request) {
	for (UserCreateRequest userCreateRequest : request.getUsers()) {
	    createUser(userCreateRequest);
	}
    }

    public boolean isUsernameUnique(String username) {
	return Objects.isNull(userRepository.findUserEntityByUsername(username).orElse(null));
    }

    public void updatePhoto(UUID id, String fileName) {
	UserEntity user = userRepository.findById(id).orElseThrow();
	user.setImageUrl(fileName);
	userRepository.save(user);
    }

    public void updateProfile(ProfileUpdateRequest request) {
	UserEntity userEntity = userRepository.findById(request.getId()).orElseThrow();
	userEntity.setFullname(request.getFullname());
	userEntity.setMail(request.getMail());
	userEntity.setStatus(request.getStatus());
	userEntity.setUsername(request.getUsername());

	if (StringUtils.isNotBlank(request.getNewPass()) && StringUtils.isNotBlank(
		request.getOldPass()) && passwordEncoder.matches(request.getOldPass(), userEntity.getPassword())) {
	    userEntity.setPassword(passwordEncoder.encode(request.getNewPass()));
	}

	userRepository.save(userEntity);
    }
}
