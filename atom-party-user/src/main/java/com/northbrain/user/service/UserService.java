package com.northbrain.user.service;

import com.northbrain.user.model.Authentication;
import org.springframework.stereotype.Service;

import com.northbrain.user.model.Constants;
import com.northbrain.user.model.User;
import com.northbrain.user.repository.IUserRepository;

import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;

@Service
public class UserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> selectByUserId(String userId) {
        return this.userRepository.findById(userId);
    }

    public Mono<Authentication> selectByLoginParams(String channelType,
                                                    String userId,
                                                    String roleId,
                                                    String organizationId,
                                                    String password) {
        return this.userRepository
                .findByUserIdAndPassword(userId, password)
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE) &&
                        Arrays.asList(user.getChannelType()).contains(channelType) &&
                        Arrays.asList(user.getRoleId()).contains(roleId) &&
                        Arrays.asList(user.getOrganizationId()).contains(organizationId))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .flatMap(user -> {
                    if(user.getUserId() == null)
                        return Mono.just(Authentication
                                .builder()
                                .result(false)
                                .build());
                    return Mono.just(Authentication
                            .builder()
                            .result(true)
                            .build());
                });
    }

    public Mono<User> createUser() {
        return this.userRepository
                .save(User.builder()
                        .type("COMMON")
                        .userName("jiakun")
                        .alias(new String[]{"jiakun"})
                        .password("jjjkkk")
                        .channelType(new String[]{"CMS"})
                        .roleId(new String[]{"Manager"})
                        .organizationId(new String[]{"GSYD"})
                        .email(new String[]{"13893190802@139.com"})
                        .phone(new String[]{"13893190802"})
                        .createTime(new Date())
                        .timestamp(new Date())
                        .status(Constants.USER_STATUS_ACTIVE)
                        .build()
                );
    }
}
