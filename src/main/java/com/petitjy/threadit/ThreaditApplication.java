package com.petitjy.threadit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ThreaditApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThreaditApplication.class, args);
	}

}
