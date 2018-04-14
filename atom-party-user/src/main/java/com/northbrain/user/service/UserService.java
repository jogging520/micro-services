package com.northbrain.user.service;

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

    public Mono<Boolean> selectByUserIdAndPassword(String userId, String password) {
        return this.userRepository
                .findByUserIdAndPassword(userId, password)
                .filter(user -> user.getStatus().equalsIgnoreCase(Constants.USER_STATUS_ACTIVE))
                .switchIfEmpty(Mono.just(User.builder().build()))
                .flatMap(user -> {
                    if(user.getUserId() == null)
                        return Mono.just(false);
                    return Mono.just(true);
                });
    }
}
