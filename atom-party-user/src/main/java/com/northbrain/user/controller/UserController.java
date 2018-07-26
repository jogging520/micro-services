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
     * @param category 类别（企业）
     * @param name 用户名
     * @param password 密码
     * @param mobile 手机号码
     * @return 校验结果
     */
    @GetMapping(Constants.USER_AUTHENTICATION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Authentication>> verifyUser(@RequestParam String serialNo,
                                                           @RequestParam String appType,
                                                           @RequestParam String category,
                                                           @RequestParam(required = false) String name,
                                                           @RequestParam(required = false) String password,
                                                           @RequestParam(required = false) String mobile) {
        if(name != null && password != null)
            return ResponseEntity.ok()
                    .body(this.userService
                            .verifyUserByNameAndPassword(serialNo, appType, category, name, password));
        else if(mobile != null)
            return ResponseEntity.ok()
                    .body(this.userService
                            .verifyByMobile(serialNo, appType, category, mobile));

        return ResponseEntity.badRequest()
                .body(null);
    }

    /**
     * 方法：创建新用户
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户
     * @return 新用户
     */
    @PostMapping(Constants.USER_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<User>> createUser(@RequestParam String serialNo,
                                                 @RequestParam String appType,
                                                 @RequestParam String category,
                                                 @RequestBody User user) {
        return ResponseEntity.ok()
                .body(this.userService
                        .createUser(serialNo, appType, category, user));
    }

    /**
     * 方法：根据ID号查找用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param userId 用户编号
     * @return 用户信息
     */
    @GetMapping(Constants.USER_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<User>> queryUser(@RequestParam String serialNo,
                                                @RequestParam String appType,
                                                @RequestParam String category,
                                                @RequestParam String userId) {
        return ResponseEntity.ok()
                .body(this.userService
                        .queryUserById(serialNo, appType, category, userId));
    }

}
