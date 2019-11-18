package com.shadows.movie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shadows.movie.bean.User;
import com.shadows.movie.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;

@RestController
@Slf4j
public class UserController {
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;
    private final UserService userService;

    public UserController(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient, UserService userService) {
        this.restTemplate = restTemplate;
        this.loadBalancerClient = loadBalancerClient;
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @GetMapping("/logUserInstance")
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user-service");
        log.info("=====user instance {}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
    }

}
