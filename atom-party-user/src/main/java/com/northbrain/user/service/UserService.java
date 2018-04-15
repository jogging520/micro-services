package com.northbrain.user.service;

import com.northbrain.user.model.Authentication;
import org.springframework.stereotype.Service;

import com.northbrain.user.model.Constants;
import com.northbrain.user.model.User;
import com.northbrain.user.repository.IUserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> selectByUserId(String userId) {
        return this.userRepository.findById(userId);
    }

    public Mono<Authentication> selectByUserIdAndPassword(String channelType,
                                                          String userId,
                                                          String roleId,
                                                          String organizationId,
                                                          String password) {
        return this.userRepository
                .findByUserIdAndPassword(userId, password)
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .flatMap(user -> {
                    if(user.getUserId() == null)
                        return Mono.just(Authentication
                                .builder()
                                .channelType(channelType)
                                .userId(userId)
                                .roleId(roleId)
                                .organizationId(organizationId)
                                .verification(false)
                                .build());
                    return Mono.just(Authentication
                            .builder()
                            .channelType(channelType)
                            .userId(userId)
                            .roleId(roleId)
                            .organizationId(organizationId)
                            .verification(true)
                            .build());
                });
    }
}
