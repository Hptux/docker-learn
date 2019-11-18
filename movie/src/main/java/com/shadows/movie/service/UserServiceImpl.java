package com.shadows.movie.service;

import com.shadows.movie.bean.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Long id) {
        User user = new User();
        user.setId(-1L);
        user.setName("默认用户");
        user.setAge(20);
        user.setBalance(new BigDecimal(300));
        user.setUsername("default");
        return user;
    }
}
