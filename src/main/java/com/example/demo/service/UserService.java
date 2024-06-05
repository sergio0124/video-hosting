package com.example.demo.service;

import com.example.demo.domain.UserCreateJsonRequest;
import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final String IMAGES_URL = "user-photos/";

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

    public UserResponse createUser(UserCreateRequest request) throws IOException {
	UserEntity userEntity = userMapper.toEntityFronCreateRequest(request);
	userEntity.setId(UUID.randomUUID());

	if (Objects.nonNull(request.getImage())) {
	    var multipartFile = request.getImage();
	    String fileName = userEntity.getId() + "." + multipartFile.getOriginalFilename().split("\\.")[1];
	    FileUploadUtil.saveFile(IMAGES_URL, fileName, multipartFile);
	    userEntity.setImageUrl(IMAGES_URL + fileName);
	}

	var password = String.valueOf(UUID.randomUUID());
	userEntity.setPassword(passwordEncoder.encode(password));
	mailSenderService.send(userEntity.getMail(), "Создание аккаунта",
		"Для логина через пользователя " + userEntity.getUsername()
			+ " воспользуйтесь паролем " + password);
	return userMapper.doMap(userRepository.save(userEntity));
    }

    public UserResponse updateUser(UserUpdateRequest request) throws IOException {
	var userEntity = userRepository.findById(request.getId()).orElseThrow();
	if (request.getImage() != null) {
	    var multipartFile = request.getImage();
	    String fileName = userEntity.getId() + "." + multipartFile.getOriginalFilename().split("\\.")[1];
	    FileUploadUtil.saveFile(IMAGES_URL, fileName, multipartFile);
	}

	userEntity.setFullname(request.getFullname());
	userEntity.setMail(request.getMail());
	userEntity.setStatus(request.getStatus());
	userEntity.setUsername(request.getUsername());
	userEntity.setRole(request.getRole());

	if (StringUtils.isNotBlank(request.getPassword())){
	    userEntity.setPassword(passwordEncoder.encode(request.getPassword()));
	}
	return userMapper.doMap(userRepository.save(userEntity));
    }

    public void createUsers(UserCreateJsonRequest request) throws IOException {
	for (UserCreateRequest userCreateRequest : request.getUsers()) {
	    createUser(userCreateRequest);
	}
    }

    public boolean isUsernameUnique(String username) {
	return Objects.isNull(userRepository.findUserEntityByUsername(username).orElse(null));
    }
}
