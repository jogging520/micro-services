package com.northbrain.user.controller;

import com.northbrain.user.model.Authentication;
import com.northbrain.user.model.User;
import com.northbrain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.northbrain.user.model.Constants;

import reactor.core.publisher.Mono;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 方法：通过两种方式验证用户信息，一种是用户名+密码，另一种是手机+验证码
     * @param appType  应用类型
     * @param userName 用户名
     * @param password 密码
     * @param mobile 手机号码
     * @return 校验结果
     */
    @GetMapping(Constants.USER_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Authentication>> verifyUserLoggingInfo(@RequestParam String appType,
                                                                      @RequestParam(required = false) String userName,
                                                                      @RequestParam(required = false) String password,
                                                                      @RequestParam(required = false) String mobile) {
        if(userName != null && password != null)
            return ResponseEntity.ok()
                    .body(this.userService
                            .queryByUserNameAndPassword(appType, userName, password));
        else if(mobile != null)
            return ResponseEntity.ok()
                    .body(this.userService
                            .queryByMobile(appType, mobile));

        return ResponseEntity.ok()
                .body(Mono.just(Authentication
                        .builder()
                        .result(false)
                        .build()));
    }

    @PostMapping(Constants.USER_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<User>> createUser() {
        return ResponseEntity.ok()
                .body(this.userService
                        .createUser());
    }

    @GetMapping(Constants.USER_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<User>> queryUser(@PathVariable String userId) {
        return ResponseEntity.ok()
                .body(this.userService
                        .queryByUserId(userId));
    }

}
