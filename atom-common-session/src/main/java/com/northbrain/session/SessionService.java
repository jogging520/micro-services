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

        Session newSession = new Session();
        newSession.setChannelType(channelType);
        newSession.setUserId(userId);
        newSession.setRoleId(roleId);
        newSession.setOrganizationId(organizationId);
        newSession.setCreateTime(new Date());
        newSession.setLoginTime(new Date());
        newSession.setTimestamp(null);
        newSession.setStatus("ACTIVE");
        newSession.setLifeTime(this.jsonWebToken.getLifeTime());

        return this.sessionRepository
                .findByTypeAndUserId(channelType, userId)
                .switchIfEmpty(
                             sessionRepository.save(newSession)
                        )
                .flatMap(session -> {
                    Session newSession1 = new Session();
                    newSession1.setSessionId(session.getSessionId());
                    newSession1.setChannelType(channelType);
                    newSession1.setUserId(userId);
                    newSession1.setRoleId(roleId);
                    newSession1.setOrganizationId(organizationId);
                    newSession1.setCreateTime(session.getCreateTime());
                    newSession1.setLoginTime(new Date());
                    newSession1.setTimestamp(null);
                    newSession1.setStatus("ACTIVE");
                    newSession1.setLifeTime(this.jsonWebToken.getLifeTime());

                    return sessionRepository.save(newSession1);
                })
                ;
    }
}
