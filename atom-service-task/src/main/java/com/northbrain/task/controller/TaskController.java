package com.northbrain.task.controller;

import com.northbrain.util.security.Crypt;
import com.northbrain.util.security.Password;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    @GetMapping("/decrypt")
    public String hello2(@RequestParam String serialNo,
                         @RequestParam Long ddd) {
        return this.crypt
                .decrypt4UserDownStream("ZuZ47YNeOoCXhxV6rwNnnUvpSN8E9tJSC52Yg5eOYtxkKTtmSeUD9jQQKvI1JaOp0SLzj9P9Vb0hZkpLTMH+paAtI9wJ/nw/UyqjQrOrBWwoxuGlpFQzoumdIeeRm/ps9CqMVbSL9y/nhMbZRH/GZkct3AAlQvfL0P5SGGsBoXU=", "app",
                        false);
    }

    @GetMapping("/path")
    public String hello1() {
        return "Hello, " + this.name;
    }

    @GetMapping("/password")
    public String hello3() {
        String salt = Password.generateSalt();
        String ciphertext = Password.encrypt("jiakun", salt);

        return "Hello, salt=" + salt + ",   ciphertext:" + ciphertext;
    }

    @GetMapping("/verify1")
    public String hello4() {
        String salt = "3716055997914a9b396bb4bbb83a0c31";
        String ciphertext = "45068726ddec3e35c1f96cee8db83098b1d7aca66217ad77ec06216cbb457dc708a9ace6a4b5381592d46623609ebf731a0af598d007bc969a14dffa2723705f";

        Boolean verified = Password.verify("jiakun", ciphertext, salt);

        return "Hello, salt=" + verified;
    }

    @GetMapping("/verify2")
    public String hello5() {
        String salt = "3716055997914a9b396bb4bbb83a0c31";
        String ciphertext = "45068726ddec3e35c1f96cee8db83098b1d7aca66217ad77ec06216cbb457dc708a9ace6a4b5381592d46623609ebf731a0af598d007bc969a14dffa2723705f";

        Boolean verified = Password.verify("liangtao", ciphertext, salt);

        return "Hello, salt=" + verified;
    }
}
