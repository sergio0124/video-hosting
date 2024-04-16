package com.example.demo.controller.implementation;

import com.example.demo.controller.SearchClassController;
import com.example.demo.domain.ClassListResponse;
import com.example.demo.domain.ClassResponse;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.ClassService;
import com.example.demo.service.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClassControllerImpl implements SearchClassController {

    private final ClassService service;
    private final VideoService videoService;

    @Override
    public ClassListResponse searchClassesByName(String className, Integer page, Integer limit) {
        log.debug("search classes '{}' '{}' '{}'", className, page, limit);
        return service.searchClasses(className, null, page, limit);
    }

    @Override
    public ClassListResponse searchClassesByTeacher(String teacherName, Integer page, Integer limit) {
        log.debug("search classes '{}' '{}' '{}'", teacherName, page, limit);
        return service.searchClasses(null, teacherName, page, limit);
    }

    @Override
    public ClassListResponse getRecommendedClasses(Integer page, Integer limit) {
        log.debug("search classes '{}' '{}'", page, limit);
        return service.searchClasses(null, null, page, limit);
    }

    @Override
    public ClassListResponse getClass(UserEntity user,
                                      Integer limit,
                                      Integer page,
                                      Boolean isAccepted) {
        return service.getClassesBySubscribe(user.getId(), page, limit, isAccepted);
    }

    @Override
    public ClassResponse getClass(UUID classId) {
        var classEntity = service.getClassById(classId);
        classEntity.setVideos(videoService.getVideosByClassId(classEntity.getId()));
        return classEntity;
    }
}
