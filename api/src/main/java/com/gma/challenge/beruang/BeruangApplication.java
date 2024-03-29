package com.gma.challenge.beruang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(
        basePackages = {
                "com.gma.challenge.beruang.category",
                "com.gma.challenge.beruang.wallet",
                "com.gma.challenge.beruang.transaction",
                "com.gma.challenge.beruang.budget",
                "com.gma.challenge.beruang.api"
        }
)
@EntityScan(
        basePackages = {
                "com.gma.challenge.beruang.category.model",
                "com.gma.challenge.beruang.wallet.model",
                "com.gma.challenge.beruang.transaction.model",
                "com.gma.challenge.beruang.budget.model"
        }
)
@EnableJpaRepositories(
        basePackages = {
                "com.gma.challenge.beruang.category.repo",
                "com.gma.challenge.beruang.wallet.repo",
                "com.gma.challenge.beruang.transaction.repo",
                "com.gma.challenge.beruang.budget.repo"
        }
)
public class BeruangApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeruangApplication.class, args);
    }
}
