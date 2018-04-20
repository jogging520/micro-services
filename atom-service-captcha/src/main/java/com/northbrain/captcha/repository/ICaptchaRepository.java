package com.northbrain.captcha.repository;

import com.northbrain.captcha.model.Captcha;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ICaptchaRepository extends ReactiveCrudRepository<Captcha, String> {
    Mono<Captcha> findFirst1ByMobileOrderByCreateTimeDesc(String mobile);
}
