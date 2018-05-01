package com.example.one.services;

import com.example.one.domain.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User findByUsername(String username) throws UsernameNotFoundException;
}
