package com.northbrain.session.service;

import com.northbrain.session.model.*;
import com.northbrain.session.repository.IAttemptHistoryRepository;
import com.northbrain.session.repository.IAttemptRepository;
import com.northbrain.session.repository.ISessionHistoryRepository;
import com.northbrain.session.repository.ISessionRepository;
import com.northbrain.session.util.JsonWebTokenUtil;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
@Log
public class SessionService {
    private final ISessionRepository sessionRepository;
    private final ISessionHistoryRepository sessionHistoryRepository;
    private final IAttemptRepository attemptRepository;
    private final IAttemptHistoryRepository attemptHistoryRepository;
    private final TokenProperty tokenProperty;

    public SessionService(ISessionRepository sessionRepository,
                          ISessionHistoryRepository sessionHistoryRepository,
                          IAttemptRepository attemptRepository,
                          IAttemptHistoryRepository attemptHistoryRepository,
                          TokenProperty tokenProperty) {
        this.sessionRepository = sessionRepository;
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.attemptRepository = attemptRepository;
        this.attemptHistoryRepository = attemptHistoryRepository;
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
     * 方法：删除会话，并移入历史库
     * @param serialNo 流水号
     * @param sessionId 会话编号
     * @return 无
     */
    public Mono<Void> deleteSession(String serialNo,
                                    String sessionId) {
        this.sessionRepository
                .findById(sessionId)
                .subscribe(session -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(session.toString());

                    this.sessionHistoryRepository
                            .save(SessionHistory.builder()
                                    .operationType(Constants.SESSION_HISTORY_DELETE)
                                    .sessionId(sessionId)
                                    .type(session.getType())
                                    .appType(session.getAppType())
                                    .user(session.getUser())
                                    .userName(session.getUserName())
                                    .mobile(session.getMobile())
                                    .loginTime(session.getLoginTime())
                                    .lifeTime(session.getLifeTime())
                                    .createTime(session.getCreateTime())
                                    .timestamp(new Date())
                                    .status(Constants.SESSION_STATUS_LOGOUT)
                                    .serialNo(serialNo)
                                    .description(session.getDescription())
                                    .build())
                            .subscribe(sessionHistory -> {
                                log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                                log.info(sessionHistory.toString());
                            });

                    this.sessionRepository
                            .deleteById(sessionId)
                            .then();
                });

        return Mono.empty().then();
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

    /**
     * 方法：查询尝试登录的次数（当日）
     * @param serialNo 流水号
     * @param userName 用户名
     * @param appType 应用类型
     * @return 尝试登录的次数
     */
    public Mono<Long> queryAttemptCount(String serialNo,
                                        String userName,
                                        String appType) {
        Date fromAttemptTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromAttemptTime);
        calendar.add(Calendar.DATE, 1);

        return this.attemptRepository
                .findByUserNameAndAppTypeAndAttemptTimeBetween(userName, appType,
                        fromAttemptTime, calendar.getTime())
                .count()
                .map(c -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(c.toString());
                    return c;
                });
    }

    /**
     * 方法：创建一条尝试登录的记录
     * @param serialNo 流水号
     * @param attempt 尝试登录实体
     * @return 创建成功的尝试登录实体
     */
    public Mono<Attempt> createAttempt(String serialNo,
                                       Attempt attempt) {
        return this.attemptRepository
                .save(attempt.setAttemptTime(new Date()))
                .map(newAttempt -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(newAttempt.toString());
                    return newAttempt;
                });
    }

    /**
     * 方法：清理尝试登录的记录，并入历史库
     * @param serialNo 流水号
     * @param userName 用户名
     * @param appType 应用类型
     * @return 空
     */
    public Flux<Void> deleteAttempts(String serialNo,
                                     String userName,
                                     String appType) {
        this.attemptRepository
                .findAllByUserNameAndAppType(userName, appType)
                .subscribe(attempt -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(attempt.toString());

                    this.attemptHistoryRepository
                            .save(AttemptHistory.builder()
                                    .operationType(Constants.SESSION_HISTORY_DELETE)
                                    .attemptId(attempt.getId())
                                    .type(attempt.getType())
                                    .userName(attempt.getUserName())
                                    .password(attempt.getPassword())
                                    .mobile(attempt.getMobile())
                                    .appType(attempt.getAppType())
                                    .attemptTime(attempt.getAttemptTime())
                                    .timestamp(new Date())
                                    .status(attempt.getStatus())
                                    .serialNo(attempt.getSerialNo())
                                    .description(attempt.getDescription())
                                    .build())
                            .subscribe(attemptHistory -> {
                                log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                                log.info(attemptHistory.toString());
                            })
                    ;

                    this.attemptRepository
                            .deleteById(attempt.getId())
                            .then();
                });

        return Flux.empty();
    }
}
