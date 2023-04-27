package com.javarush.maximov.catalog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class App {
    public static void main(String[] args) {
        log.info("Starting the application");
        SpringApplication.run(App.class, args);
        log.info("Application started successfully");
    }
}
