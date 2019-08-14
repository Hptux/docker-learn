package com.shadows.user.controller;

import com.shadows.user.bean.User;
import com.shadows.user.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepo.findById(id).orElse(null);
    }
}
