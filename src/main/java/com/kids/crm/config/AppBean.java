package com.kids.crm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.kids.crm.repository")
@EnableMongoRepositories(basePackages = "com.kids.crm.mongo.repository")
public class AppBean {
}
