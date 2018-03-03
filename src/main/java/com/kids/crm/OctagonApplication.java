package com.kids.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaRepositories(basePackages = "com.kids.crm.repository")
@EnableMongoRepositories(basePackages = "com.kids.crm.mongo.repository")
@SpringBootApplication
public class OctagonApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctagonApplication.class, args);
	}
}
