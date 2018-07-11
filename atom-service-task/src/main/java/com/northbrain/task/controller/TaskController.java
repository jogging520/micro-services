package com.northbrain.task.controller;

import com.northbrain.util.security.Crypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TaskController {

    private final Crypt crypt;

    @Value("${logging.path}")
    private String name;

    public TaskController(Crypt crypt) {
        this.crypt = crypt;
    }


    @GetMapping("/zookeeper")
    public String hello() {
        return this.crypt
                .encrypt4UserDownStream("abcdef", "app");
    }

    @GetMapping("/decrypt")
    public String hello2() {
        return this.crypt
                .decrypt4UserDownStream("ZuZ47YNeOoCXhxV6rwNnnUvpSN8E9tJSC52Yg5eOYtxkKTtmSeUD9jQQKvI1JaOp0SLzj9P9Vb0hZkpLTMH+paAtI9wJ/nw/UyqjQrOrBWwoxuGlpFQzoumdIeeRm/ps9CqMVbSL9y/nhMbZRH/GZkct3AAlQvfL0P5SGGsBoXU=", "app");
    }

    @GetMapping("/path")
    public String hello1() {
        return "Hello, " + this.name;
    }
}
