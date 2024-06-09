package com.example.demo.controller;

import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/app/visits")
    public void createVisit(@AuthenticationPrincipal UserEntity user, @RequestParam UUID videoId) {
	visitService.markVisited(videoId, user.getId());
    }
}
