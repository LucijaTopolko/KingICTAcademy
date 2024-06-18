package com.academy.kingictacademy.user.repository;

import com.academy.kingictacademy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean findUserByUsername(String username);
}
