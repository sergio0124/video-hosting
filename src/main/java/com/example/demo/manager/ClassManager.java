package com.example.demo.manager;

import com.example.demo.domain.ClassListResponse;
import com.example.demo.domain.entity.ClassEntity;
import com.example.demo.repository.ClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClassManager {

    private final ClassRepository repository;

    public List<ClassEntity> searchClassesByName(String className, Pageable pageable) {
        var result = repository.findClassEntitiesByName(className, pageable);
        log.info("find {} rows by search class by classname '{}', page {} limit {}",
                result.size(),
                className,
                pageable.getPageNumber(),
                pageable.getPageSize());
        return result;
    }

    public List<ClassEntity> searchClassesByTeacher(String teacherName, Pageable pageable) {
        var result = repository.findClassEntitiesByUserUsernameContains(teacherName, pageable);
        log.info("find {} rows by search class by teacher username '{}', page {} limit {}",
                result.size(),
                teacherName,
                pageable.getPageNumber(),
                pageable.getPageSize());
        return result;
    }

    public List<ClassEntity> getAllClasses(Pageable pageable) {
        var result = repository.findAll(pageable).getContent();
        log.info("find {} rows by search classes, page {} limit {}",
                result.size(),
                pageable.getPageNumber(),
                pageable.getPageSize());
        return result;
    }

    public List<ClassEntity> getClassesBySubscribe(UUID id, Boolean isAccepted, Pageable pageable) {
        var result = repository.findClassEntitiesBySubscribesUserIdAndSubscribesIsAccepted(id,
                isAccepted,
                pageable);
        log.info("find {} rows by search classes, page {} limit {}",
                result.size(),
                pageable.getPageNumber(),
                pageable.getPageSize());
        return result;
    }

    public ClassEntity getClassById(UUID classId) {
        return repository.findById(classId).orElseThrow();
    }
}
