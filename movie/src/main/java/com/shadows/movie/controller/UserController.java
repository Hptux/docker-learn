package com.shadows.movie.controller;

import com.shadows.movie.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class UserController {
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    public UserController(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
    }

    private final static String userService = "http://user-service";

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject(userService+ "/user/" + id, User.class);
    }

    @GetMapping("/logUserInstance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-service");
        log.info("=====user instance {}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

}
