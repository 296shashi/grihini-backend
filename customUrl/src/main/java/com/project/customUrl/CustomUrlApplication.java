package com.project.customUrl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.project.customUrl")
@EnableJpaRepositories("com.project.customUrl")
@EntityScan("com.project.customUrl")
@Configuration
public class CustomUrlApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomUrlApplication.class, args);
    }
}
