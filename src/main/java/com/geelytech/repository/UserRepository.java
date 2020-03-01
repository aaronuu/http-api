package com.geelytech.repository;

import com.geelytech.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findUserByUsernameAndPassword(String username, String password);
}