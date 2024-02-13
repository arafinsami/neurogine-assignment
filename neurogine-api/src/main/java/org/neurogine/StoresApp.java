package org.neurogine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableDiscoveryClient
@SpringBootApplication
@EnableMongoRepositories
@EntityScan(basePackages = "org.neurogine.entity")
public class StoresApp {

    public static void main(String[] args) {
        SpringApplication.run(StoresApp.class, args);
    }
}
