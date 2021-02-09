package com.project.grihini.panel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.project.grihini")
@EnableJpaRepositories("com.project.grihini.core")
@EntityScan("com.project.grihini.core")
@Configuration
public class PanelApplication {

  public static void main(String[] args) {
    SpringApplication.run(PanelApplication.class, args);
  }

}
