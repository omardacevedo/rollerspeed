package org.example.rollerspeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
public class RollerSpeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(RollerSpeedApplication.class, args);
        System.out.println("aplicacion ejecutandose");
    }

}
