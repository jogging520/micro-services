package com.northbrain.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.northbrain.user.model.Constants;
import com.northbrain.user.service.UserService;

import reactor.core.publisher.Mono;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(Constants.USER_HTTP_REQUEST_MAPPING)
    public Mono<String> verifyUserLogInfo(@RequestParam String userId,
                                          @RequestParam String password) {

        return this.userService
                .selectByUserIdAndPassword(userId, password)
                .switchIfEmpty(Mono.error(new RuntimeException(Constants.USER_VERIFY_FAILURE)))
                .flatMap(user -> Mono.just(Constants.USER_VERIFY_SUCCESS));

    }

}
