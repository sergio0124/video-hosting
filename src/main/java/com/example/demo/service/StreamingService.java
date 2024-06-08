package com.javatechie.streaming;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StreamingService {

    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads";
    private static final String FORMAT="classpath:videos/%s.mp4";

    private final ResourceLoader resourceLoader;

}
