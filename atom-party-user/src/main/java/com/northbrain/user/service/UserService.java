package com.northbrain.user.service;

import com.northbrain.user.model.*;
import com.northbrain.util.security.Crypt;
import com.northbrain.util.security.Password;
import com.northbrain.util.timer.Clock;
import org.springframework.stereotype.Service;

import com.northbrain.user.repository.IUserHistoryRepository;
import com.northbrain.user.repository.IUserRepository;

import lombok.extern.java.Log;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
@Log
public class UserService {
    private final IUserRepository userRepository;
    private final IUserHistoryRepository userHistoryRepository;
    private final Crypt crypt;

    public UserService(IUserRepository userRepository,
                       IUserHistoryRepository userHistoryRepository,
                       Crypt crypt) {
        this.userRepository = userRepository;
        this.userHistoryRepository = userHistoryRepository;
        this.crypt = crypt;
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
     * @param name 用户名
     * @param password 密码
     * @return 校验结果
     */
    public Mono<Authentication> verifyByUserNameAndPassword(String serialNo,
                                                            String appType,
                                                            String name,
                                                            String password) {
        return this.userRepository
                .findByName(this.crypt.decrypt4UserDownStream(name, appType, true))
                .flatMap(user -> {
                    log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                    if(user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE) &&
                            user.getAppTypes() != null &&
                            Arrays.asList(user.getAppTypes()).contains(appType) &&
                            Password.verify(user.getPassword(),
                                    this.crypt.decrypt4UserDownStream(password, appType, true),
                                    this.crypt.decrypt4System(user.getSalt()))) {
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
     * @param appType 应用类型
     * @param user 用户
     * @return 新用户
     */
    public Mono<User> createUser(String serialNo,
                                 String appType,
                                 User user) {
        String salt = Password.generateSalt();
        log.info(user.toString());

        return this.userRepository
                .findByIdOrName(user.getId(), this.crypt.decrypt4UserDownStream(user.getName(), appType, false))
                .map(newUser -> newUser.setStatus(Constants.USER_ERRORCODE_HAS_EXISTS))
                .switchIfEmpty(this.userRepository
                        .save(user
                                .setName(this.crypt.encrypt4System(
                                        this.crypt.decrypt4UserDownStream(user.getName(), appType, false)))
                                .setPassword(Password.encrypt(this.crypt.decrypt4UserDownStream(user.getPassword(), appType, false), salt))
                                .setSalt(this.crypt.encrypt4System(salt))
                                .setRealName(this.crypt.encrypt4System(
                                        this.crypt.decrypt4UserDownStream(user.getRealName(), appType, false)))
                                .setStatus(Constants.USER_STATUS_ACTIVE)
                                .setCreateTime(Clock.currentTime())
                                .setTimestamp(Clock.currentTime())
                                .setSerialNo(serialNo))
                        .map(newUser -> {
                            log.info(Constants.USER_OPERATION_SERIAL_NO + serialNo);

                            this.userHistoryRepository
                                    .save(UserHistory.builder()
                                            .operationType(Constants.USER_HISTORY_CREATE)
                                            .userId(newUser.getId())
                                            .type(newUser.getType())
                                            .name(newUser.getName())
                                            .password(newUser.getPassword())
                                            .realName(newUser.getRealName())
                                            .avatar(newUser.getAvatar())
                                            .appTypes(newUser.getAppTypes())
                                            .roles(newUser.getRoles())
                                            .permissions(newUser.getPermissions())
                                            .affiliations(newUser.getAffiliations())
                                            .mobiles(newUser.getMobiles())
                                            .emails(newUser.getEmails())
                                            .weChats(newUser.getWeChats())
                                            .createTime(newUser.getCreateTime())
                                            .timestamp(Clock.currentTime())
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
