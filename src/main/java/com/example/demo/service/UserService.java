package com.example.demo.service;

import com.example.demo.domain.UserCreateRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.UserUpdateRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final String IMAGES_URL = "user-photos/";

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

	return users.stream().map(userMapper::doMap).collect(Collectors.toList());
    }

    public void createUser(UserCreateRequest request) throws IOException {
	UserEntity userEntity = userMapper.toEntityFronCreateRequest(request);
	userEntity.setId(UUID.randomUUID());

	var multipartFile = request.getImage();
	String fileName = userEntity.getId() + "." + multipartFile.getOriginalFilename().split("\\.")[1];
	FileUploadUtil.saveFile(IMAGES_URL, fileName, multipartFile);

	userEntity.setImageUrl(IMAGES_URL + fileName);
	userRepository.save(userEntity);
    }

    public void updateUser(UserUpdateRequest request) throws IOException {
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
	userRepository.save(userEntity);
    }
}
