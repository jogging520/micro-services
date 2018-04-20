package com.northbrain.captcha.repository;

import com.northbrain.captcha.model.CaptchaHistory;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ICaptchaHistoryRepository extends ReactiveCrudRepository<CaptchaHistory, String> {
}
