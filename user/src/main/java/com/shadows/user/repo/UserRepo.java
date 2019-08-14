package com.shadows.user.repo;

import com.shadows.user.bean.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
