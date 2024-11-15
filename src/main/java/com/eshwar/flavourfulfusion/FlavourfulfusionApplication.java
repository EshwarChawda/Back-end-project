package com.eshwar.flavourfulfusion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FlavourfulfusionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlavourfulfusionApplication.class, args);
    }

}
