package org.musinsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class MusinsaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusinsaApplication.class, args);
    }
}