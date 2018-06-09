package com.northbrain.user.controller;

import com.northbrain.user.model.Authentication;
import com.northbrain.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public Mono<Authentication> verifyUserLoggingInfo(@RequestParam String appType,
                                                      @RequestParam(required = false) String userName,
                                                      @RequestParam(required = false) String password,
                                                      @RequestParam(required = false) String mobile) {
        if(userName != null && password != null)
            return this.userService.selectByUserNameAndPassword(appType, userName, password);
        else if(mobile != null)
            return this.userService.selectByMobile(appType, mobile);

        return Mono.just(Authentication
                .builder()
                .result(false)
                .build());
    }

    @PostMapping(Constants.USER_HTTP_REQUEST_MAPPING)
    public Mono<User> createUser() {
        return this.userService
                .createUser();
    }

}
