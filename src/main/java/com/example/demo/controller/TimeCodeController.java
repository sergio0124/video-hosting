package com.example.demo.controller;

import com.example.demo.domain.TimeCodeRequest;
import com.example.demo.domain.TimeCodeResponse;
import com.example.demo.domain.entity.TimeCodeEntity;
import com.example.demo.service.TimeCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TimeCodeController {

    private final TimeCodeService timeCodeService;

    @PostMapping("/app/timecodes")
    public void createTimeCode(@RequestBody TimeCodeRequest timeCodeRequest) {
        timeCodeService.createTimeCode(timeCodeRequest);
    }

    @GetMapping("/app/timecodes/video/{videoId}")
    public List<TimeCodeResponse> getTimeCodes(@PathVariable UUID videoId) {
        return timeCodeService.getTimeCodesByVideo(videoId);
    }

    @DeleteMapping("/app/timecodes/{id}")
    public void deleteTimeCode(@PathVariable UUID id) {
        timeCodeService.deleteTimeCode(id);
    }

    @PutMapping("/app/timecodes")
    public void updateTimeCode(@RequestBody TimeCodeRequest request) {
        timeCodeService.updateTimeCode(request);
    }
}
