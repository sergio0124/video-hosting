package com.example.demo.controller;

import com.example.demo.domain.ClassListResponse;
import com.example.demo.domain.SubscribeRequest;
import com.example.demo.domain.UserRegisterRequest;
import com.example.demo.domain.entity.UserEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.UUID;

@Tag(name = "subscribes", description = "API для работы с подписками")
@Validated
public interface SubscribeController {

    @Operation(summary = "Подписаться на курс")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PostMapping(value = "api/v1/subscribe")
    void subscribeClass(@AuthenticationPrincipal UserEntity user,
                        @RequestBody @Valid SubscribeRequest request);


    @Operation(summary = "Принять подписку")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PatchMapping(value = "api/v1/subscribe")
    void acceptSubscribe(@AuthenticationPrincipal UserEntity user,
                         @RequestBody @Valid SubscribeRequest request);


    @Operation(summary = "Отменить подписку")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "api/v1/subscribe")
    void deleteSubscribe(@AuthenticationPrincipal UserEntity user,
                         @RequestBody @Valid SubscribeRequest request);


    @Operation(summary = "Подписан ли пользователь на курс")
    @GetMapping(value = "api/v1/subscribe")
    Boolean isSubscribed(@AuthenticationPrincipal UserEntity user,
                          @RequestBody @Valid SubscribeRequest request);
}
