package com.northbrain.session.controller;

import com.northbrain.session.model.Constants;
import com.northbrain.session.model.Token;
import com.northbrain.session.service.SessionService;
import com.northbrain.session.model.Session;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * 方法：根据ID号查找会话信息
     * @param serialNo 流水号
     * @param sessionId 会话编号
     * @return 会话信息
     */
    @GetMapping(Constants.SESSION_SPECIFIED_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Session>> selectSessionById(@RequestParam String serialNo,
                                                           @PathVariable String sessionId) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .querySessionById(serialNo, sessionId));
    }

    /**
     * 方法：登录
     * @param appType 应用类型
     * @param userId 用户编号
     * @param userName 用户姓名
     * @param mobile 手机号码
     * @return 令牌
     */
    @PostMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Token>> login(@RequestParam String serialNo,
                                             @RequestParam String appType,
                                             @RequestParam String userId,
                                             @RequestParam(required = false) String userName,
                                             @RequestParam(required = false) String mobile) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .createSession(serialNo, appType, userId, userName, mobile));
    }

    /**
     * 方法：校验JWT的有效性
     * @param jwt json web token
     * @return 校验结果（正常、异常、失效、无会话等），如果无效，那么lifetime=0
     */
    @GetMapping(Constants.SESSION_HTTP_REQUEST_MAPPING_JWT)
    public ResponseEntity<Mono<Token>> verifyJWT(@RequestParam String serialNo,
                                                 @RequestParam String jwt) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .verifyJWT(serialNo, jwt));
    }

}
