package com.northbrain.task.controller;

import com.northbrain.util.model.SecurityProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TaskController {

    private final SecurityProperty securityProperty;

    @Value("${logging.path}")
    private String name;

    public TaskController(SecurityProperty securityProperty) {
        this.securityProperty = securityProperty;
    }


    @GetMapping("/zookeeper")
    public String hello() {
        return "Hello, " + this.securityProperty.getAppDownPrivateKey();
    }
}
