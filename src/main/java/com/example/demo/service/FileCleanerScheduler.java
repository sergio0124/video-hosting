package com.example.demo.service;

import com.example.demo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FileCleanerScheduler {

    Map<String, LocalDateTime> files = new HashMap<>();

    private final VideoRepository videoRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";

    // Время в минутах до очистки, если файл не загружен
    private static final Integer DIF_TIME = 30;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws IOException {
	var curTime = LocalDateTime.now();
	for (Map.Entry<String, LocalDateTime> entry : files.entrySet()) {
	    long minutes = ChronoUnit.MINUTES.between(curTime, entry.getValue());
	    if (minutes > DIF_TIME) {
		var fileByImage = videoRepository.findVideoEntityByImageUrl(entry.getKey());
		var fileByVideo = videoRepository.findVideoEntityByVideoUrl(entry.getKey());
		if (Objects.isNull(fileByVideo) && Objects.isNull(fileByImage)) {
		    Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, entry.getKey());
		    Files.delete(fileNameAndPath);
		} else {
		    files.remove(entry.getKey());
		}
	    }
	}

    }

    public void putFile(String fileName) {
	files.put(fileName, LocalDateTime.now());
    }
}
