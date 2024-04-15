package com.example.demo.service;

import com.example.demo.domain.ClassListResponse;
import com.example.demo.domain.ClassResponse;
import com.example.demo.domain.UserResponse;
import com.example.demo.domain.entity.ClassEntity;
import com.example.demo.manager.ClassManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
public class ClassService {

    private final ClassManager manager;

    private final DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    public ClassListResponse searchClasses(String className, String teacherName, Integer page, Integer limit) {
        List<ClassEntity> classEntities;
        Pageable pageable = PageRequest.of(page, limit, Sort.by("creationDate").descending());
        if (className != null) {
            classEntities = manager.searchClassesByName(className, pageable);
        } else if (teacherName != null) {
            classEntities = manager.searchClassesByTeacher(teacherName, pageable);
        } else {
            classEntities = manager.getAllClasses(pageable);
        }
        return classesListToResponse(classEntities);
    }

    public ClassListResponse getClassesBySubscribe(UUID id, Integer page, Integer limit, Boolean isAccepted) {
        var classEntities = manager.getClassesBySubscribe(id, isAccepted,
                PageRequest.of(page, limit, Sort.by("creationDate").descending()));
        return classesListToResponse(classEntities);
    }

    public ClassListResponse classesListToResponse(List<ClassEntity> entities) {
        return ClassListResponse
                .builder()
                .classes(entities.stream()
                        .map(c -> ClassResponse
                                .builder()
                                .id(c.getId())
                                .creationDate(df.format(c.getCreationDate()))
                                .description(c.getDescription())
                                .name(c.getName())
                                .user(UserResponse
                                        .builder()
                                        .username(c.getUser().getUsername())
                                        .role(c.getUser().getRole())
                                        .status(c.getUser().getStatus())
                                        .fullname(c.getUser().getFullname())
                                        .build())
                                .build())
                        .toList())
                .build();
    }
}
