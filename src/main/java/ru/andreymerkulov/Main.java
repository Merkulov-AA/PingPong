package ru.andreymerkulov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
    }
}