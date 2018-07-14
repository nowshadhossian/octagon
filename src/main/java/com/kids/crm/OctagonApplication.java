package com.kids.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class OctagonApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctagonApplication.class, args);
	}
}
