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

    public Mono<Authentication> selectByUserNameAndPassword(String appType,
                                                            String userName,
                                                            String password) {
        return this.userRepository
                .findByUserNameAndPassword(userName, password)
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE) &&
                        Arrays.asList(user.getAppTypes()).contains(appType))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .flatMap(user -> {
                    if(user.getUserId() == null)
                        return Mono.just(Authentication
                                .builder()
                                .result(false)
                                .build());
                    return Mono.just(Authentication
                            .builder()
                            .userId(user.getUserId())
                            .userName(user.getUserName())
                            .mobiles(user.getMobiles())
                            .appType(appType)
                            .result(true)
                            .build());
                });
    }

    public Mono<Authentication> selectByMobileAndCaptcha(String appType,
                                                         String mobile,
                                                         String captcha) {
        return this.userRepository
                .findByMobilesContaining(mobile)
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE) &&
                        Arrays.asList(user.getAppTypes()).contains(appType))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .flatMap(user -> {
                    if(user.getUserId() == null)
                        return Mono.just(Authentication
                                .builder()
                                .result(false)
                                .build());
                    return Mono.just(Authentication
                            .builder()
                            .userId(user.getUserId())
                            .userName(user.getUserName())
                            .mobiles(user.getMobiles())
                            .appType(appType)
                            .result(true)
                            .build());
                });
    }

    //先通过普通的KEY-AUTH（USERNAME+PASSWORD和MOBILE+CAPTCHA）获取用户的ID、组织机构等校验信息
    //用户要不要进行相应的选择，如角色和机构？如果需要，则反向再查询组织机构和角色信息
    //最后根据这些信息再进行会话登录。

    public Mono<User> createUser() {
        return this.userRepository
                .save(User.builder()
                        .type("COMMON")
                        .userName("jiakun")
                        .password("jjjkkk")
                        .appTypes(new String[]{"CMS"})
                        .roleIds(new String[]{"Manager"})
                        .organizationIds(new String[]{"GSYD"})
                        .emails(new String[]{"13893190802@139.com"})
                        .mobiles(new String[]{"13893190802"})
                        .createTime(new Date())
                        .timestamp(new Date())
                        .status(Constants.USER_STATUS_ACTIVE)
                        .build()
                );
    }
}
