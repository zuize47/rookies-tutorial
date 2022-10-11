package com.nashtech.rookies;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class RookiesTutorialWebApplication {

	public static void main(String[] args) {
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "web");
		SpringApplication.run(RookiesTutorialWebApplication.class, args);

	}
}
