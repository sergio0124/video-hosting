package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ResourceController {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    private final UserService userService;

    @GetMapping(value = "/image/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String filename) throws IOException {
	Path fileNameAndPath;
	fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, filename);
	if (!Files.exists(fileNameAndPath)) {
	    fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, "def.jpg");
	}

	return Files.readAllBytes(fileNameAndPath);
    }

    @PostMapping("/upload/user/{id}")
    public void uploadImage(@RequestParam("file") MultipartFile file, @PathVariable UUID id) throws IOException {
	String fileName = id + "." + file.getOriginalFilename().split("\\.")[1];
	Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, fileName);
	Files.write(fileNameAndPath, file.getBytes());

	userService.updatePhoto(id, fileName);
    }
}
