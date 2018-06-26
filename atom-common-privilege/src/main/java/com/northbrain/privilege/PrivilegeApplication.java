package com.northbrain.privilege;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.northbrain"})
public class PrivilegeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivilegeApplication.class, args);
	}
}
