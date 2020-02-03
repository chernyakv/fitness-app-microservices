package com.chernyak.authservice.config.mongodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.chernyak.authservice.repository")
public class MongoConfig {

}
