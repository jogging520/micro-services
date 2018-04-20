package com.northbrain.captcha.service;

import com.northbrain.captcha.model.Captcha;
import com.northbrain.captcha.model.Constants;
import com.northbrain.captcha.repository.ICaptchaHistoryRepository;
import com.northbrain.captcha.repository.ICaptchaRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class CaptchaService {
    private final ICaptchaRepository captchaRepository;
    private final ICaptchaHistoryRepository captchaHistoryRepository;


    public CaptchaService(ICaptchaRepository captchaRepository, ICaptchaHistoryRepository captchaHistoryRepository) {
        this.captchaRepository = captchaRepository;
        this.captchaHistoryRepository = captchaHistoryRepository;
    }

    public Mono<Void> sendCaptcha(String mobile) {
        return this.captchaRepository
                .save(Captcha.builder()
                        .mobile(mobile)
                        .captcha("123456")
                        .createTime(new Date())
                        .timestamp(new Date())
                        .status(Constants.CAPTCHA_STATUS_SUCCESS)
                        .build()
                )
                .then();
    }

    public Mono<Captcha> selectCaptchaByMobile(String mobile, String captchaCode) {
        return this.captchaRepository
                .findFirst1ByMobileOrderByCreateTimeDesc(mobile)
                .filter(captcha -> captcha.getCaptcha().equals(captchaCode));
    }
}
