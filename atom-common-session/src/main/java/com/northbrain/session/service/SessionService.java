package com.northbrain.session.service;

import com.northbrain.session.model.*;
import com.northbrain.session.repository.IAttemptHistoryRepository;
import com.northbrain.session.repository.IAttemptRepository;
import com.northbrain.session.repository.ISessionHistoryRepository;
import com.northbrain.session.repository.ISessionRepository;
import com.northbrain.session.util.JsonWebTokenUtil;
import com.northbrain.util.security.Crypt;

import com.northbrain.util.timer.Clock;
import com.northbrain.util.tracer.StackTracer;
import org.springframework.stereotype.Service;

import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@Log
public class SessionService {
    private final ISessionRepository sessionRepository;
    private final ISessionHistoryRepository sessionHistoryRepository;
    private final IAttemptRepository attemptRepository;
    private final IAttemptHistoryRepository attemptHistoryRepository;
    private final TokenProperty tokenProperty;
    private final Crypt crypt;

    public SessionService(ISessionRepository sessionRepository,
                          ISessionHistoryRepository sessionHistoryRepository,
                          IAttemptRepository attemptRepository,
                          IAttemptHistoryRepository attemptHistoryRepository,
                          TokenProperty tokenProperty,
                          Crypt crypt) {
        this.sessionRepository = sessionRepository;
        this.sessionHistoryRepository = sessionHistoryRepository;
        this.attemptRepository = attemptRepository;
        this.attemptHistoryRepository = attemptHistoryRepository;
        this.tokenProperty = tokenProperty;
        this.crypt = crypt;
    }

    /**
     * 方法：创建会话
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param user 用户编号
     * @param userName 用户名
     * @param mobile 手机号码
     * @param address 客户端IP地址
     * @return 令牌
     */
    public Mono<Token> createSession(String serialNo,
                                     String appType,
                                     String category,
                                     String user,
                                     String userName,
                                     String mobile,
                                     String address) {
        log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);

        return this.sessionRepository
                .findByAppTypeAndCategoryAndStatusAndUserName(appType, category,
                        Constants.SESSION_STATUS_LOGIN, userName)
                .switchIfEmpty(
                        this.sessionRepository.save(Session
                                .builder()
                                .type(Constants.SESSION_TYPE_COMMON)
                                .appType(appType)
                                .category(category)
                                .user(user)
                                .userName(userName)
                                .mobile(mobile)
                                .address(address)
                                .createTime(Clock.currentTime())
                                .loginTime(Clock.currentTime())
                                .timestamp(Clock.currentTime())
                                .status(Constants.SESSION_STATUS_LOGIN)
                                .lifeTime(this.tokenProperty.getLifeTime())
                                .build()))
                .flatMap(session -> this.sessionRepository.save(Session
                        .builder()
                        .id(session.getId())
                        .type(Constants.SESSION_TYPE_COMMON)
                        .appType(appType)
                        .category(category)
                        .user(user)
                        .userName(userName)
                        .mobile(mobile)
                        .address(address)
                        .createTime(session.getCreateTime())
                        .loginTime(Clock.currentTime())
                        .timestamp(Clock.currentTime())
                        .status(Constants.SESSION_STATUS_LOGIN)
                        .lifeTime(this.tokenProperty.getLifeTime())
                        .build())
                )
                .flatMap(
                        session -> {
                            try {
                                return Mono.just(Token.builder()
                                        .session(session.getId())
                                        .user(user)
                                        .lifeTime(this.tokenProperty.getLifeTime())
                                        .jwt(JsonWebTokenUtil.generateJsonWebToken(session.getId(), appType, address,
                                                tokenProperty.getKey(), tokenProperty.getCompany(), tokenProperty.getAudience(),
                                                tokenProperty.getIssuer(), tokenProperty.getLifeTime()))
                                        .downPublicKey(this.crypt.getDownPublicKey(appType))
                                        .upPrivateKey(this.crypt.getUpPrivateKey(appType))
                                        .status(Constants.SESSION_ERRORCODE_SUCCESS)
                                        .build());
                            } catch (Exception e) {
                                StackTracer.printException(e);
                                return Mono.just(Token.builder()
                                        .lifeTime(0L)
                                        .status(Constants.SESSION_ERRORCODE_TOKEN_EXPIRED)
                                        .build());
                            }
                        }
                );
    }

    /**
     * 方法：删除会话，并移入历史库
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param session 会话编号
     * @param address 客户端IP地址
     * @return 无
     */
    public Mono<Void> deleteSession(String serialNo,
                                    String appType,
                                    String category,
                                    String session,
                                    String address) {
        this.sessionRepository
                .findById(session)
                .filter(newSession -> newSession.getAppType().equalsIgnoreCase(appType))
                .filter(newSession -> newSession.getCategory().equalsIgnoreCase(category))
                .filter(newSession -> newSession.getAddress().equalsIgnoreCase(address))
                .subscribe(newSession -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(newSession.toString());

                    this.sessionHistoryRepository
                            .save(SessionHistory.builder()
                                    .operationType(Constants.SESSION_HISTORY_DELETE)
                                    .sessionId(session)
                                    .type(newSession.getType())
                                    .appType(newSession.getAppType())
                                    .category(newSession.getCategory())
                                    .user(newSession.getUser())
                                    .userName(newSession.getUserName())
                                    .mobile(newSession.getMobile())
                                    .address(newSession.getAddress())
                                    .loginTime(newSession.getLoginTime())
                                    .lifeTime(newSession.getLifeTime())
                                    .createTime(newSession.getCreateTime())
                                    .timestamp(Clock.currentTime())
                                    .status(Constants.SESSION_STATUS_LOGOUT)
                                    .serialNo(serialNo)
                                    .description(newSession.getDescription())
                                    .build())
                            .subscribe(sessionHistory -> {
                                log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                                log.info(sessionHistory.toString());
                            });

                    this.sessionRepository
                            .deleteById(session)
                            .then();
                });

        return Mono.empty().then();
    }

    /**
     * 方法：校验JWT的有效性
     * 对于当前未失效的的JWT都可以使用，每次按照ID查找，只要找到其中之一便有效。
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param address 客户端IP地址
     * @param jwt json web token
     * @return 校验结果（正常、异常、失效、无会话等），如果无效，那么lifetime=0
     */
    public Mono<Token> verifyJWT(String serialNo,
                                 String appType,
                                 String category,
                                 String address,
                                 String jwt) {
        log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);

        try {
            Map<String, String> claims = JsonWebTokenUtil
                    .parseJsonWebToken(jwt, tokenProperty.getKey(), tokenProperty.getCompany(),
                            tokenProperty.getAudience(), tokenProperty.getIssuer());


            return this.sessionRepository
                    .findById(claims.get(Constants.SESSION_JWT_CLAIMS_SESSION))
                    .filter(session -> session.getStatus().equalsIgnoreCase(Constants.SESSION_STATUS_LOGIN))
                    .filter(session -> session.getAppType().equalsIgnoreCase(appType) &&
                            session.getAppType().equalsIgnoreCase(claims.get(Constants.SESSION_JWT_CLAIMS_APP_TYPE)))
                    .filter(session -> session.getCategory().equalsIgnoreCase(category))
                    .filter(session -> session.getAddress().equalsIgnoreCase(address) &&
                            session.getAddress().equalsIgnoreCase(claims.get(Constants.SESSION_JWT_CLAIMS_ADDRESS)))
                    .filter(session -> session.getLoginTime().getTime() + session.getLifeTime() >= System.currentTimeMillis())
                    .switchIfEmpty(Mono.just(Session.builder().build()))
                    .flatMap(session -> {
                        if(session.getId() == null)
                            return Mono.just(Token.builder().lifeTime(0L).build());

                        return Mono.just(Token
                                .builder()
                                .session(session.getId())
                                .lifeTime(tokenProperty.getLifeTime())
                                .jwt(jwt)
                                .status(Constants.SESSION_ERRORCODE_SUCCESS)
                                .build());
                    });
        } catch (Exception e) {
            StackTracer.printException(e);
            return Mono.just(Token.builder()
                    .lifeTime(0L)
                    .status(Constants.SESSION_ERRORCODE_TOKEN_EXPIRED)
                    .build());
        }
    }

    /**
     * 方法：查询尝试登录的次数（当日）
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param userName 用户名
     * @return 尝试登录的次数
     */
    public Mono<Long> queryAttemptCount(String serialNo,
                                        String appType,
                                        String category,
                                        String userName) {
        return this.attemptRepository
                .findByUserNameAndAppTypeAndCategoryAndAttemptTimeBetween(
                        this.crypt.decrypt4UserDownStream(userName, appType, true),
                        appType, category, Clock.currentDate(), Clock.tomorrowDate())
                .count()
                .map(c -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(Constants.SESSION_ATTEMPT_TIMES + c.toString());
                    return c;
                });
    }

    /**
     * 方法：创建一条尝试登录的记录
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param category 类别（企业）
     * @param attempt 尝试登录实体
     * @return 创建成功的尝试登录实体
     */
    public Mono<Attempt> createAttempt(String serialNo,
                                       String appType,
                                       String category,
                                       Attempt attempt) {

        log.info(attempt.toString());
        return this.attemptRepository
                .save(attempt
                        .setAppType(appType)
                        .setCategory(category)
                        .setUserName(this.crypt.decrypt4UserDownStream(attempt.getUserName(),
                                attempt.getAppType(), true))
                        .setAttemptTime(Clock.currentTime())
                        .setTimestamp(Clock.currentTime()))
                .map(newAttempt -> {
                    log.info(Constants.SESSION_OPERATION_SERIAL_NO + serialNo);
                    log.info(newAttempt.toString());
                    return newAttempt.setStatus(Constants.SESSION_ERRORCODE_SUCCESS);
                });
    }

    /**
     * 方法：清理尝试登录的记录，并入历史库
     * @param serialNo 流水号
     * @param userName 用户名
     * @param appType 应用类型
     * @param category 类别（企业）
     * @return 空
     */
    public Mono<Void> deleteAttempts(String serialNo,
                               String userName,
                               String appType,
                               String category) {
        this.attemptRepository
                .findAllByUserNameAndAppTypeAndCategory(userName, appType, category)
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
                                    .category(attempt.getCategory())
                                    .attemptTime(attempt.getAttemptTime())
                                    .timestamp(Clock.currentTime())
                                    .status(attempt.getStatus())
                                    .serialNo(serialNo)
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

        return Mono.empty().then();
    }

    /**
     * 方法：获取临时加密公钥
     * @param appType 应用类型
     * @return 临时公钥
     */
    public String queryTemporaryPublicKey(String appType) {
        return this.crypt.getTemporaryPublicKey(appType);
    }
}
