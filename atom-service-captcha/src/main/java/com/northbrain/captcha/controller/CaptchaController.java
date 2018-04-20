package com.northbrain.captcha.controller;

import com.northbrain.captcha.model.Captcha;
import com.northbrain.captcha.model.Constants;
import com.northbrain.captcha.service.CaptchaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CaptchaController {
    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
    }

    /**
     * 方法：发送验证码
     * @param mobile 手机号码
     * @return 无
     */
    @PostMapping(Constants.CAPTCHA_HTTP_REQUEST_MAPPING)
    public Mono<Void> send(String mobile) {
        return this.captchaService
                .sendCaptcha(mobile);
    }

    /**
     * 方法：获取用户已经发送的最后一条验证码
     * @param mobile 手机号码
     * @return 验证码
     */
    @GetMapping(Constants.CAPTCHA_HTTP_REQUEST_MAPPING)
    public Mono<Captcha> selectCaptcha(String mobile) {
        return this.captchaService
                .selectCaptchaByMobile(mobile);
    }
}
