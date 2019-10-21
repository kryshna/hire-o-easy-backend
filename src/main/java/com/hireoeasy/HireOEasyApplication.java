package com.hireoeasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.hireoeasy")
@EnableAutoConfiguration
@EnableJpaRepositories("com.spring.app.repository")
public class HireOEasyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HireOEasyApplication.class, args);
	}

}
