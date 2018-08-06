package com.northbrain.session.controller;

import com.northbrain.session.model.Attempt;
import com.northbrain.session.model.Constants;
import com.northbrain.session.model.Token;
import com.northbrain.session.service.SessionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
public class SessionController {
    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    /**
     * 方法：登录
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户编号
     * @param userName 用户姓名
     * @param mobile 手机号码
     * @param address 客户端IP地址
     * @return 令牌
     */
    @PostMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Token>> login(@RequestParam String serialNo,
                                             @RequestParam String appType,
                                             @RequestParam String category,
                                             @RequestParam String user,
                                             @RequestParam(required = false) String userName,
                                             @RequestParam(required = false) String mobile,
                                             @RequestParam String address) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .createSession(serialNo, appType, category, user,
                                userName, mobile, address));
    }

    /**
     * 方法：登出
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param session 会话编号
     * @param address 客户端IP地址
     * @return 无
     */
    @DeleteMapping(Constants.SESSION_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Void>> logout(@RequestParam String serialNo,
                                             @RequestParam String appType,
                                             @RequestParam String category,
                                             @RequestParam String session,
                                             @RequestParam String address) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .deleteSession(serialNo, session, appType, category, address));
    }

    /**
     * 方法：校验JWT的有效性
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param address 客户端IP地址
     * @param jwt json web token
     * @return 校验结果（正常、异常、失效、无会话等），如果无效，那么lifetime=0
     */
    @GetMapping(Constants.SESSION_JWT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Token>> verifyJWT(@RequestParam String serialNo,
                                                 @RequestParam String appType,
                                                 @RequestParam String category,
                                                 @RequestParam String address,
                                                 @RequestParam String jwt) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .verifyJWT(serialNo, appType, category, address, jwt));
    }

    /**
     * 方法：查询尝试登录的次数（当日）
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param userName 用户名
     * @return 尝试登录的次数
     */
    @GetMapping(Constants.SESSION_ATTEMPT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Long>> queryAttemptCount(@RequestParam String serialNo,
                                                        @RequestParam String appType,
                                                        @RequestParam String category,
                                                        @RequestParam String userName) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .queryAttemptCount(serialNo, appType, category, userName));
    }

    /**
     * 方法：创建一条尝试登录的记录
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param attempt 尝试登录实体
     * @return 创建成功的尝试登录实体
     */
    @PostMapping(Constants.SESSION_ATTEMPT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Attempt>> createAttempt(@RequestParam String serialNo,
                                                       @RequestParam String appType,
                                                       @RequestParam String category,
                                                       @RequestBody Attempt attempt) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .createAttempt(serialNo, appType, category, attempt));
    }

    /**
     * 方法：清理尝试登录的记录，并入历史库
     * @param serialNo 流水号
     * @param userName 用户名
     * @param appType 应用类型
     * @param category 类别（企业）
     * @return 空
     */
    @DeleteMapping(Constants.SESSION_ATTEMPT_HTTP_REQUEST_MAPPING)
    public ResponseEntity<Mono<Void>> deleteAttempts(@RequestParam String serialNo,
                                                     @RequestParam String userName,
                                                     @RequestParam String appType,
                                                     @RequestParam String category) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .deleteAttempts(serialNo, userName, appType, category));
    }

    /**
     * 方法：获取临时加密公钥
     * @param appType 应用类型
     * @return 临时公钥
     */
    @GetMapping(Constants.SESSION_TEMPORARY_HTTP_REQUEST_MAPPING)
    public ResponseEntity<String> queryTemporaryPublicKey(@RequestParam String appType) {
        return ResponseEntity.ok()
                .body(this.sessionService
                        .queryTemporaryPublicKey(appType));
    }
}
