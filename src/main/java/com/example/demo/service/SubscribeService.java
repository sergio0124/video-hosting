package com.example.demo.service;

import com.example.demo.domain.ClassListResponse;
import com.example.demo.domain.ClassResponse;
import com.example.demo.domain.SubscribeRequest;
import com.example.demo.domain.UserResponse;
import com.example.demo.manager.SubscribeManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubscribeService {

    private final SubscribeManager manager;

    public void subscribeClass(String username, SubscribeRequest request) {
        manager.createSubscribe(username, request);
    }

    public void acceptSubscribe(String username, SubscribeRequest request) {
        manager.acceptSubscribe(username, request);
    }

    public void deleteSubscribe(String username, SubscribeRequest request) {
        manager.deleteSubscribe(username, request);
    }

}
