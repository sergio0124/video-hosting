package com.example.demo.manager;

import com.example.demo.domain.ClassListResponse;
import com.example.demo.domain.SubscribeRequest;
import com.example.demo.domain.entity.ClassEntity;
import com.example.demo.domain.entity.SubscribeEntity;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.SubscribeRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class SubscribeManager {

    private final SubscribeRepository repository;
    private final UserRepository userRepository;
    private final ClassRepository classRepository;

    public void createSubscribe(String username, SubscribeRequest request) {
        var user = userRepository.findUserEntityByUsername(username).orElseThrow();
        var vclass = classRepository.findById(request.getClassId()).orElseThrow();
        var subscribe = new SubscribeEntity();
        subscribe.setUser(user);
        subscribe.setVclass(vclass);
        subscribe.setIsAccepted(Boolean.FALSE);
        repository.save(subscribe);
        log.info("subscribe of user '{}' to class with id '{}' is created",
                username,
                request.getClassId());
    }

    public void acceptSubscribe(String username, SubscribeRequest request) {
        var subscribe = repository.findSubscribeEntityByvclassIdAndUserUsername(
                        request.getClassId(),
                        username)
                .orElseThrow();
        subscribe.setIsAccepted(Boolean.TRUE);
        repository.save(subscribe);
        log.info("subscribe of user '{}' to class with id '{}' is accepted",
                username,
                request.getClassId());
    }

    public void deleteSubscribe(String username, SubscribeRequest request) {
        var subscribe = repository.findSubscribeEntityByvclassIdAndUserUsername(
                        request.getClassId(),
                        username)
                .orElseThrow();
        repository.delete(subscribe);
        log.info("subscribe of user '{}' to class with id '{}' is deleted",
                username,
                request.getClassId());
    }

}
