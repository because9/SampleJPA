package com.sample.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.sample.jpa.repository", "com.sample.jpa.controller",
        "com.sample.jpa.service" })
@EnableAutoConfiguration
public class SampleJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleJpaApplication.class, args);
    }

}
