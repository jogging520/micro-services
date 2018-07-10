package com.northbrain.task.controller;

import com.northbrain.util.model.SecurityProperty;
import com.northbrain.util.model.TokenProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TaskController {

    private final SecurityProperty securityProperty;
    private final TokenProperty tokenProperty;

    @Value("${logging.path}")
    private String name;

    public TaskController(SecurityProperty securityProperty, TokenProperty tokenProperty) {
        this.securityProperty = securityProperty;
        this.tokenProperty = tokenProperty;
    }


    @GetMapping("/zookeeper")
    public String hello() {
        return "Hello, " + this.securityProperty.getAppDownPrivateKey();
    }
}
