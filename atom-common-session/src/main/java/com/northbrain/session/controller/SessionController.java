package com.northbrain.session.controller;

import com.northbrain.session.model.Constants;
import com.northbrain.session.model.Token;
import com.northbrain.session.service.SessionService;
import com.northbrain.session.model.Session;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public Flux<Session> listing() {
        return this.sessionService.selectAllSessions()
                .map(session -> {
                    System.out.println(session);
                    return session;
                });
    }

    @GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING_BY_ID)
    public Mono<Session> selectSessionById(@PathVariable String sessionId) {
        return this.sessionService
                .selectSessionById(sessionId)
                .map(session -> {
                    System.out.println(session);
                    return session;
                });
    }

    /**
     * 方法：登录
     * @param appType 应用类型
     * @param userName 用户姓名
     * @param password 密码
     * @param mobile 手机号码
     * @return 令牌
     */
    @PostMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public Mono<Token> login(@RequestParam String appType,
                             @RequestParam(required = false) String userName,
                             @RequestParam(required = false) String password,
                             @RequestParam(required = false) String mobile) {
        return this.sessionService
                .createSession(appType, userName, mobile);
    }

    /**
     * 方法：校验JWT的有效性
     * @param jwt json web token
     * @return 校验结果（正常、异常、失效、无会话等），如果无效，那么lifetime=0
     */
    @GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING_JWT)
    public Mono<Token> verifyJWT(@RequestParam String jwt) {
        return this.sessionService
                .verifyJWT(jwt);
    }

}
