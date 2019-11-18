package com.shadows.movie.service;

import com.shadows.movie.bean.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", fallback = UserServiceImpl.class)
public interface UserService {

    @GetMapping(value = "/user/{id}")
    User findById(@PathVariable Long id);
}
