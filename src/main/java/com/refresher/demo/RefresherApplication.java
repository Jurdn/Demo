package com.refresher.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.refresher.demo.Repository")
public class RefresherApplication {

    public static void main(String[] args) {
        SpringApplication.run(RefresherApplication.class, args);
    }

}
