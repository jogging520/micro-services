package com.northbrain.user.service;

import com.northbrain.user.model.*;
import com.northbrain.user.repository.IVerificationHistoryRepository;
import com.northbrain.user.repository.IVerificationRepository;
import org.springframework.stereotype.Service;

import com.northbrain.user.repository.IUserHistoryRepository;
import com.northbrain.user.repository.IUserRepository;

import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Date;

@Service
@Log
public class UserService {
    private final IUserRepository userRepository;
    private final IUserHistoryRepository userHistoryRepository;
    private final IVerificationRepository verificationRepository;
    private final IVerificationHistoryRepository verificationHistoryRepository;

    public UserService(IUserRepository userRepository,
                       IUserHistoryRepository userHistoryRepository,
                       IVerificationRepository verificationRepository,
                       IVerificationHistoryRepository verificationHistoryRepository) {
        this.userRepository = userRepository;
        this.userHistoryRepository = userHistoryRepository;
        this.verificationRepository = verificationRepository;
        this.verificationHistoryRepository = verificationHistoryRepository;
    }

    /**
     * 方法：根据ID号查找用户信息
     * @param serialNo 流水号
     * @param userId 用户编号
     * @return 用户信息
     */
    public Mono<User> queryUserById(String serialNo,
                                    String userId) {
        return this.userRepository
                .findByIdAndStatus(userId, Constants.USER_STATUS_ACTIVE)
                .map(user -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);
                    log.info(user.toString());
                    return user.setPassword(null)
                            .setStatus(null);
                });
    }

    /**
     * 方法：根据用户名+密码验证用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param userName 用户名
     * @param password 密码
     * @return 校验结果
     */
    public Mono<Authentication> verifyByUserNameAndPassword(String serialNo,
                                                            String appType,
                                                            String userName,
                                                            String password) {
        //判断是否密码输入错误，如果错误，则记录一次
        //这个要修改，把错误的次数作为一个服务调用，在前端判断。
        return this.userRepository
                .findByUserName(userName)
                .flatMap(user -> {
                    if(!user.getPassword().equalsIgnoreCase(password)) {
                        this.verificationRepository
                                .save(Verification.builder()
                                        .type(Constants.USER_COUNTER_TYPE_COMMON)
                                        .appType(appType)
                                        .user(user.getId())
                                        .loginTime(new Date())
                                        .timestamp(new Date())
                                        .status(Constants.USER_COUNTER_STATUS_WRONG_PASSWORD)
                                        .serialNo(serialNo)
                                        .description(Constants.USER_COUNTER_AUTO_DESCRIPTION)
                                        .build())
                                .subscribe(verification -> {
                                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);
                                    log.info(Constants.USER_COUNTER_STATUS_WRONG_PASSWORD);
                                });

                        return this.verificationRepository
                                .findByUserAndAppType(user.getId(), appType)
                                .count()
                                .flatMap(c -> {
                                    if(c > Constants.USER_LOGGING_MAX_ATTEMPT_TIME)
                                        return Mono.just(Authentication
                                                .builder()
                                                .user(user.getId())
                                                .authType(Constants.USER_LOGGING_TYPE_PASSWORD)
                                                .result(true)
                                                .build());

                                    return Mono.just(Authentication
                                            .builder()
                                            .result(false)
                                            .build());
                                });

                    } else if(user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE) &&
                            user.getAppTypes() != null &&
                            Arrays.asList(user.getAppTypes()).contains(appType)) {
                        return Mono.just(Authentication
                                .builder()
                                .user(user.getId())
                                .authType(Constants.USER_LOGGING_TYPE_PASSWORD)
                                .result(true)
                                .build());
                    }

                    return Mono.just(Authentication
                            .builder()
                            .result(false)
                            .build());
                })
                .switchIfEmpty(Mono.just(Authentication
                        .builder()
                        .result(false)
                        .build()));
    }

    /**
     * 方法：根据手机号码验证用户信息
     * @param serialNo 流水号
     * @param appType 应用类型
     * @param mobile 手机号码
     * @return 校验结果
     */
    public Mono<Authentication> verifyByMobile(String serialNo,
                                               String appType,
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
                            .user(user.getId())
                            .authType(Constants.USER_LOGGING_TYPE_CAPTCHA)
                            .result(true)
                            .build());
                })
                .map(authentication -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);
                    log.info(authentication.toString());
                    return authentication;
                });
    }

    /**
     * 方法：创建新用户
     * @param serialNo 流水号
     * @param user 用户
     * @return 新用户
     */
    public Mono<User> createUser(String serialNo,
                                 User user) {
        return this.userRepository
                .findByIdOrUserName(user.getId(), user.getUserName())
                .map(newUser -> newUser.setStatus(Constants.USER_ERRORCODE_HAS_EXISTS))
                .switchIfEmpty(this.userRepository
                        .save(user
                                .setStatus(Constants.USER_STATUS_ACTIVE)
                                .setCreateTime(new Date())
                                .setTimestamp(new Date())
                                .setSerialNo(serialNo))
                        .map(newUser -> {
                            log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);
                            log.info(newUser.toString());

                            this.userHistoryRepository
                                    .save(UserHistory.builder()
                                            .operationType(Constants.USER_HISTORY_CREATE)
                                            .userId(newUser.getId())
                                            .type(newUser.getType())
                                            .userName(newUser.getUserName())
                                            .password(newUser.getPassword())
                                            .realName(newUser.getRealName())
                                            .avatar(newUser.getAvatar())
                                            .appTypes(newUser.getAppTypes())
                                            .roles(newUser.getRoles())
                                            .permissions(newUser.getPermissions())
                                            .affiliations(newUser.getAffiliations())
                                            .mobiles(newUser.getMobiles())
                                            .emails(newUser.getEmails())
                                            .wechates(newUser.getWechates())
                                            .createTime(newUser.getCreateTime())
                                            .timestamp(new Date())
                                            .status(newUser.getStatus())
                                            .serialNo(serialNo)
                                            .description(newUser.getDescription())
                                            .build())
                                    .subscribe(userHistory -> {
                                        log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);
                                        log.info(userHistory.toString());
                                    });

                            return newUser.setStatus(Constants.USER_ERRORCODE_SUCCESS);
                        }));
    }
}
