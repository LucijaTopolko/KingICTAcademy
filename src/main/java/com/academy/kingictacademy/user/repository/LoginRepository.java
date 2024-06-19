package com.academy.kingictacademy.user.repository;

import com.academy.kingictacademy.user.entity.LoginData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginData, Long> {
    Optional<LoginData> findByUsername(String username);
}
