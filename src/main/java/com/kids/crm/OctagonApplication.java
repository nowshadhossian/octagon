package com.kids.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableJpaRepositories(basePackages = "com.kids.crm.repository")
@EnableMongoRepositories(basePackages = "com.kids.crm.mongo.repository")
@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties
public class OctagonApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctagonApplication.class, args);
	}
}
