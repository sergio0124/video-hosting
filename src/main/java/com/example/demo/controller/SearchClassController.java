package com.example.demo.controller;

import com.example.demo.domain.ClassListResponse;
import com.example.demo.domain.ClassResponse;
import com.example.demo.domain.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Validated
@Tag(name = "search class", description = "API для поиска курсов лекций")
public interface SearchClassController {

    @Operation(summary = "Поиск курсов по названию")
    @GetMapping(value = "api/v1/classes/search/name")
    ClassListResponse searchClassesByName(@RequestParam String className,
                                          @RequestParam(defaultValue = "0", required = false) Integer page,
                                          @RequestParam(defaultValue = "10", required = false) Integer limit);

    @Operation(summary = "Поиск курсов по имени преподавателя")
    @GetMapping(value = "api/v1/classes/search/teacher")
    ClassListResponse searchClassesByTeacher(@RequestParam String teacherName,
                                             @RequestParam(defaultValue = "0", required = false) Integer page,
                                             @RequestParam(defaultValue = "10", required = false) Integer limit);

    @Operation(summary = "Получение рекомендаций курсов")
    @GetMapping(value = "api/v1/classes/recommended")
    ClassListResponse getRecommendedClasses(@RequestParam(defaultValue = "0", required = false) Integer page,
                                            @RequestParam(defaultValue = "10", required = false) Integer limit);

    @Operation(summary = "Поиск курсов по подписке")
    @GetMapping(value = "api/v1/classes/subscribe")
    ClassListResponse getClass(@AuthenticationPrincipal UserEntity user,
                               @RequestParam(defaultValue = "10") Integer limit,
                               @RequestParam(defaultValue = "0") Integer page,
                               @RequestParam(defaultValue = "True") Boolean isAccepted);

    @Operation(summary = "Получение информации по курсу")
    @GetMapping(value = "api/v1/class/{classId}")
    ClassResponse getClass(@PathVariable UUID classId);
}
