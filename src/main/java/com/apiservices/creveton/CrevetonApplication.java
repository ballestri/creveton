package com.apiservices.creveton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "com.apiservices.creveton.config")
public class CrevetonApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrevetonApplication.class, args);
	}

}
