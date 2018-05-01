package com.example.one.repositories.security;

import com.example.one.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmail(String email);
}
