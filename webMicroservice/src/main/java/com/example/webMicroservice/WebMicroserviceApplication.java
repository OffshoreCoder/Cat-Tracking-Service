package com.example.webMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.common", "com.example.webMicroservice"})
@EnableJpaRepositories(basePackages = "com.example.common.repository")
public class WebMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMicroserviceApplication.class, args);
    }
}
