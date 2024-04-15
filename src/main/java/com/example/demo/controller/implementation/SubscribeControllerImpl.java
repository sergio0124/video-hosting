package com.example.demo.controller.implementation;

import com.example.demo.domain.SubscribeRequest;
import com.example.demo.domain.entity.UserEntity;
import com.example.demo.service.SubscribeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SubscribeControllerImpl implements com.example.demo.controller.SubscribeController {

    private final SubscribeService service;

    @Override
    public void subscribeClass(UserEntity user, SubscribeRequest request) {
        log.debug("subscribe class with id {} by user '{}'",
                request.getClassId(),
                user.getUsername());
        service.subscribeClass(user.getUsername(), request);
    }

    @Override
    public void acceptSubscribe(UserEntity user, SubscribeRequest request) {
        log.debug("accept subscribe class with id {} by user '{}'",
                request.getClassId(),
                user.getUsername());
        service.acceptSubscribe(user.getUsername(), request);
    }

    @Override
    public void deleteSubscribe(UserEntity user, SubscribeRequest request) {
        log.debug("delete subscribe class with id {} by user '{}'",
                request.getClassId(),
                user.getUsername());
        service.deleteSubscribe(user.getUsername(), request);
    }

    @Override
    public Boolean isSubscribed(UserEntity user, SubscribeRequest request) {
        return Boolean.TRUE;
    }

}
