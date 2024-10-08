package com.etjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableCaching
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ClassroomApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassroomApplication.class, args);
    }

}
