package com.northbrain.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.northbrain"})
public class FamilyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyApplication.class, args);
	}
}
