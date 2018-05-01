package com.example.one.services;

import com.example.one.domain.User;

public interface UserService {
    User findUserByEmail(String email);
    void saveUser(User user);
}
