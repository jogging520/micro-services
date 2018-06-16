package com.northbrain.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.northbrain"})
public class SessionApplication {
	public static void main(String[] args) {
		SpringApplication.run(SessionApplication.class, args);
	}
}
