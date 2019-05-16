package com.shadows.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloApplication {
    @GetMapping("/")
    public String hello(@RequestParam(value = "name", required = false) String name) {
        name = StringUtils.hasText(name) ? name : "shadows";
        return "Hello " + name + "!";
    }

    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }

}
