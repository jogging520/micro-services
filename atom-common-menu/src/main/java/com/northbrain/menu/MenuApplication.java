package com.northbrain.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.northbrain"})
public class MenuApplication {
	public static void main(String[] args) {
		SpringApplication.run(MenuApplication.class, args);
	}
}
