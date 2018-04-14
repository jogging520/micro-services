package com.northbrain.session.service;

import com.northbrain.session.model.Constants;
import com.northbrain.session.model.Token;
import com.northbrain.session.model.TokenProperty;
import com.northbrain.session.model.Session;
import com.northbrain.session.repository.ISessionHistoryRepository;
import com.northbrain.session.repository.ISessionRepository;
import com.northbrain.session.util.JsonWebTokenUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;

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

    public Flux<Session> selectAllSessions() {
        return this.sessionRepository.findAll();
    }

    public Mono<Session> selectSessionById(String sessionId) {
        return this.sessionRepository.findById(sessionId);
    }

    //如果不存在，那么创建一条，否则返回已经登录。
    public Mono<String> createSession(String channelType, String userId, String roleId, String organizationId) {
        return this.sessionRepository
                .findByChannelTypeAndUserId(channelType, userId)
                .filter(session -> session.getStatus().equalsIgnoreCase(Constants.SESSION_STATUS_LOGIN))
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
                                     .status(Constants.SESSION_STATUS_LOGIN)
                                     .lifeTime(this.tokenProperty.getLifeTime())
                                     .build()))
                .flatMap(session -> sessionRepository.save(Session
                        .builder()
                        .sessionId(session.getSessionId())
                        .channelType(channelType)
                        .userId(userId)
                        .roleId(roleId)
                        .organizationId(organizationId)
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
                                return Mono.just(JsonWebTokenUtil.generateJsonWebToken(session.getSessionId(),
                                        userId, roleId, organizationId, tokenProperty.getKey(), tokenProperty.getCompany(),
                                        tokenProperty.getAudience(), tokenProperty.getIssuer(), tokenProperty.getLifeTime()));
                            } catch (Exception e) {
                                e.printStackTrace();
                                return Mono.empty();
                            }
                        }
                );
    }

    public Mono<Token> verifyJWT(String jwt) {
        try {
            Map<String, String> claims = JsonWebTokenUtil
                    .parseJsonWebToken(jwt, tokenProperty.getKey(), tokenProperty.getCompany(),
                            tokenProperty.getAudience(), tokenProperty.getIssuer());

            return this.sessionRepository
                    .findBySessionId(claims.get(Constants.SESSION_JWT_CLAIMS_SESSION_ID))
                    .filter(session -> session.getStatus().equalsIgnoreCase(Constants.SESSION_STATUS_LOGIN))
                    .switchIfEmpty(Mono.just(Session.builder().build()))
                    .flatMap(session -> {
                        if(session.getSessionId() == null)
                            return Mono.just(Token.builder().lifeTime(0L).build());

                        return Mono.just(Token
                                .builder()
                                .userId(claims.get(Constants.SESSION_JWT_CLAIMS_USER_ID))
                                .roleId(claims.get(Constants.SESSION_JWT_CLAIMS_ROLE_ID))
                                .organizationId(claims.get(Constants.SESSION_JWT_CLAIMS_ORGANIZATION_ID))
                                .lifeTime(tokenProperty.getLifeTime())
                                .build());
                    });
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(Token.builder().lifeTime(0L).build());
        }
    }
}
