package com.northbrain.session.service;

import com.northbrain.session.model.Constants;
import com.northbrain.session.model.TokenProperty;
import com.northbrain.session.model.Session;
import com.northbrain.session.repository.ISessionHistoryRepository;
import com.northbrain.session.repository.ISessionRepository;
import com.northbrain.session.util.JsonWebTokenUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
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

    public Flux<Session> findAllSessions() {
        return this.sessionRepository.findAll().log(tokenProperty.toString());
    }

    //如果不存在，那么创建一条，否则返回已经登录。
    public Mono<String> saveSession(String channelType, String userId, String roleId, String organizationId) {
        return this.sessionRepository
                .findByTypeAndUserId(channelType, userId)
                .switchIfEmpty(
                             sessionRepository.save(Session
                                     .builder()
                                     .channelType(channelType)
                                     .userId(userId)
                                     .roleId(roleId)
                                     .organizationId(organizationId)
                                     .createTime(new Date())
                                     .loginTime(new Date())
                                     .timestamp(null)
                                     .status(Constants.SESSION_STATUS_HAS_LOGGED)
                                     .lifeTime(this.tokenProperty.getLifeTime())
                                     .build()))
                .flatMap(session -> sessionRepository.save(Session
                        .builder()
                        .sessionId(session.sessionId())
                        .channelType(channelType)
                        .userId(userId)
                        .roleId(roleId)
                        .organizationId(organizationId)
                        .createTime(session.createTime())
                        .loginTime(new Date())
                        .timestamp(new Date())
                        .status(Constants.SESSION_STATUS_HAS_LOGGED)
                        .lifeTime(this.tokenProperty.getLifeTime())
                        .build())
                )
                .flatMap(
                        session -> {
                            try {
                                return Mono.just(JsonWebTokenUtil.generateJsonWebToken(session.sessionId(),
                                        userId, roleId, organizationId, tokenProperty.getKey(), tokenProperty.getCompany(),
                                        tokenProperty.getAudience(), tokenProperty.getIssuer(), tokenProperty.getLifeTime()));
                            } catch (Exception e) {
                                e.printStackTrace();
                                return Mono.empty();
                            }
                        }
                );
    }
}
