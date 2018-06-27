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

    public Mono<User> queryByUserId(String userId) {
        return this.userRepository
                .findById(userId)
                .map(user -> user.setPassword(null)
                        .setStatus(null));
    }

    public Mono<Authentication> queryByUserNameAndPassword(String appType,
                                                           String userName,
                                                           String password) {
        return this.userRepository
                .findByUserNameAndPassword(userName, password)
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE) &&
                        user.getAppTypes() != null &&
                        Arrays.asList(user.getAppTypes()).contains(appType))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .flatMap(user -> {
                    if(user.getId() == null)
                        return Mono.just(Authentication
                                .builder()
                                .result(false)
                                .build());
                    return Mono.just(Authentication
                            .builder()
                            .userId(user.getId())
                            .authType(Constants.USER_LOGGING_TYPE_PASSWORD)
                            .result(true)
                            .build());
                });
    }

    public Mono<Authentication> queryByMobile(String appType,
                                              String mobile) {
        return this.userRepository
                .findByMobilesContaining(mobile)
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE) &&
                        user.getAppTypes() != null &&
                        Arrays.asList(user.getAppTypes()).contains(appType))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .flatMap(user -> {
                    if(user.getId() == null)
                        return Mono.just(Authentication
                                .builder()
                                .result(false)
                                .build());
                    return Mono.just(Authentication
                            .builder()
                            .userId(user.getId())
                            .authType(Constants.USER_LOGGING_TYPE_CAPTCHA)
                            .result(true)
                            .build());
                });
    }

    public Mono<User> createUser() {
        return this.userRepository
                .save(User.builder()
                        .type("COMMON")
                        .userName("jiakun")
                        .password("jjjkkk")
                        .appTypes(new String[]{"CMS"})
                        .roleIds(new String[]{"Manager"})
                        .emails(new String[]{"13893190802@139.com"})
                        .mobiles(new String[]{"13893190802"})
                        .createTime(new Date())
                        .timestamp(new Date())
                        .status(Constants.USER_STATUS_ACTIVE)
                        .build()
                );
    }
}
