package com.northbrain.session.service;

import com.northbrain.session.model.Constants;
import com.northbrain.session.model.Token;
import com.northbrain.session.model.TokenProperty;
import com.northbrain.session.model.Session;
import com.northbrain.session.repository.ISessionHistoryRepository;
import com.northbrain.session.repository.ISessionRepository;
import com.northbrain.session.util.JsonWebTokenUtil;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;

@Service
@Log
public class SessionService {
    private final ISessionRepository sessionRepository;
    private final ISessionHistoryRepository sessionHistoryRepository;
    private final TokenProperty tokenProperty;

    public SessionService(ISessionRepository sessionRepository, ISessionHistoryRepository sessionHistoryRepository,
                          TokenProperty tokenProperty) {
        this.sessionRepository = sessionRepository;
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.tokenProperty = tokenProperty;
    }

    /**
     * 方法：根据ID号查找会话信息
     * @param serialNo 流水号
     * @param sessionId 会话编号
     * @return 会话信息
     */
    public Mono<Session> querySessionById(String serialNo,
                                          String sessionId) {
        return this.sessionRepository
                .findById(sessionId)
                .map(session -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(session.toString());

                    return session;
                });
    }

    /**
     * 方法：创建会话
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param user 用户编号
     * @param userName 用户名
     * @param mobile 手机号码
     * @return 令牌
     */
    public Mono<Token> createSession(String serialNo,
                                     String appType,
                                     String user,
                                     String userName,
                                     String mobile) {
        log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);

        return this.sessionRepository
                .findByAppTypeAndUserName(appType, userName)
                .filter(session -> session.getStatus().equalsIgnoreCase(Constants.SESSION_STATUS_LOGIN))
                .switchIfEmpty(
                        this.sessionRepository.save(Session
                                .builder()
                                .type(Constants.SESSION_TYPE_COMMON)
                                .appType(appType)
                                .user(user)
                                .userName(userName)
                                .mobile(mobile)
                                .createTime(new Date())
                                .loginTime(new Date())
                                .timestamp(new Date())
                                .status(Constants.SESSION_STATUS_LOGIN)
                                .lifeTime(this.tokenProperty.getLifeTime())
                                .build()))
                .flatMap(session -> this.sessionRepository.save(Session
                        .builder()
                        .id(session.getId())
                        .type(Constants.SESSION_TYPE_COMMON)
                        .appType(appType)
                        .user(user)
                        .userName(userName)
                        .mobile(mobile)
                        .createTime(session.getCreateTime())
                        .loginTime(new Date())
                        .timestamp(new Date())
                        .status(Constants.SESSION_STATUS_LOGIN)
                        .lifeTime(this.tokenProperty.getLifeTime())
                        .build())
                )
                .flatMap(
                        session -> {
                            try {
                                return Mono.just(Token
                                                .builder()
                                                .session(session.getId())
                                                .user(user)
                                                .lifeTime(this.tokenProperty.getLifeTime())
                                                .jwt(JsonWebTokenUtil.generateJsonWebToken(session.getId(), appType,
                                                        tokenProperty.getKey(), tokenProperty.getCompany(), tokenProperty.getAudience(),
                                                        tokenProperty.getIssuer(), tokenProperty.getLifeTime()))
                                                .build()
                                        );
                            } catch (Exception e) {
                                e.printStackTrace();
                                return Mono.just(Token.builder().lifeTime(0L).build());
                            }
                        }
                );
    }

    /**
     * 方法：校验JWT的有效性
     * 对于当前未失效的的JWT都可以使用，每次按照ID查找，只要找到其中之一便有效。
     * @param serialNo 流水号
     * @param jwt json web token
     * @return 校验结果（正常、异常、失效、无会话等），如果无效，那么lifetime=0
     */
    public Mono<Token> verifyJWT(String serialNo,
                                 String jwt) {
        log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);

        try {
            Map<String, String> claims = JsonWebTokenUtil
                    .parseJsonWebToken(jwt, tokenProperty.getKey(), tokenProperty.getCompany(),
                            tokenProperty.getAudience(), tokenProperty.getIssuer());

            return this.sessionRepository
                    .findById(claims.get(Constants.SESSION_JWT_CLAIMS_SESSION_ID))
                    .filter(session -> session.getStatus().equalsIgnoreCase(Constants.SESSION_STATUS_LOGIN))
                    .filter(session -> session.getCreateTime().getTime() + session.getLifeTime() >= System.currentTimeMillis())
                    .switchIfEmpty(Mono.just(Session.builder().build()))
                    .flatMap(session -> {
                        if(session.getId() == null)
                            return Mono.just(Token.builder().lifeTime(0L).build());

                        return Mono.just(Token
                                .builder()
                                .session(session.getId())
                                .lifeTime(tokenProperty.getLifeTime())
                                .jwt(jwt)
                                .build());
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(Token.builder().lifeTime(0L).build());
        }
    }
}
