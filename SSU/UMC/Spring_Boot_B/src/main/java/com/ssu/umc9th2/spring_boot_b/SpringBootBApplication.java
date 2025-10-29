package com.ssu.umc9th2.spring_boot_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringBootBApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootBApplication.class, args);
    }

}
