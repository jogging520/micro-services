package com.northbrain.session;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class SessionService {
    private final ISessionRepository sessionRepository;
    private final JsonWebToken jsonWebToken;

    public SessionService(ISessionRepository sessionRepository, JsonWebToken jsonWebToken) {
        this.sessionRepository = sessionRepository;
        this.jsonWebToken = jsonWebToken;
    }

    public Flux<Session> findAllSessions() {
        return this.sessionRepository.findAll().log(jsonWebToken.toString());
    }

    //如果不存在，那么创建一条，否则返回已经登录。
    public Mono<Session> saveSession(String channelType, String userId, String roleId, String organizationId) {
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
                             .status("ACTIVE")
                             .lifeTime(this.jsonWebToken.getLifeTime())
                             .build())
                        )
                .flatMap(session -> sessionRepository.save(Session.builder()
                    .sessionId(session.sessionId())
                    .channelType(channelType)
                    .userId(userId)
                    .roleId(roleId)
                    .organizationId(organizationId)
                    .createTime(session.createTime())
                    .loginTime(new Date())
                    .timestamp(new Date())
                    .status("ACTIVE")
                    .lifeTime(this.jsonWebToken.getLifeTime())
                    .build())
                )
                ;
    }
}
