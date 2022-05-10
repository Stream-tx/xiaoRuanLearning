package com.example.onlinetoolsservice.repository;

import com.example.onlinetoolsservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);
    User findByUserId(long id);
    User findByUsername(String username);
}