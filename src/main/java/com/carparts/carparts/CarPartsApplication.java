package com.carparts.carparts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class CarPartsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarPartsApplication.class, args);
    }

}
