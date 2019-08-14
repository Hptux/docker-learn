package com.shadows.movie.controller;

import com.shadows.movie.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {
    private final RestTemplate restTemplate;

    public UserController(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://localhost:8080/user/" + id, User.class);
    }

}
