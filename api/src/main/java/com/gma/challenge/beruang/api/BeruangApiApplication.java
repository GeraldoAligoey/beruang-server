package com.gma.challenge.beruang.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.gma.challenge.beruang.service",
                "com.gma.challenge.beruang.api"
        }
)
@EntityScan(
        basePackages = {
                "com.gma.challenge.beruang.model"
        }
)
@EnableJpaRepositories(
        basePackages = {
                "com.gma.challenge.beruang.repo"
        }
)
public class BeruangApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeruangApiApplication.class, args);
    }
}
