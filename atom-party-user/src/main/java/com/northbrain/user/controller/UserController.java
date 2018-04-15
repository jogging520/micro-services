package com.northbrain.user.controller;

import com.northbrain.user.model.Authentication;
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
    public Mono<Authentication> verifyUserLogInfo(@RequestParam String channelType,
                                                  @RequestParam String userId,
                                                  @RequestParam String roleId,
                                                  @RequestParam String organizationId,
                                                  @RequestParam String password) {
        return this.userService
                .selectByUserIdAndPassword(channelType, userId, roleId, organizationId, password);
    }

}
