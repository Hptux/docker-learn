package com.shadows.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaShadowsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaShadowsApplication.class, args);
    }

}
