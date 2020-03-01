package com.geelytech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.geelytech.entity")
@EnableJpaRepositories(basePackages = {"com.geelytech.repository"})
public class HttpApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(HttpApiApplication.class, args);
  }
}
