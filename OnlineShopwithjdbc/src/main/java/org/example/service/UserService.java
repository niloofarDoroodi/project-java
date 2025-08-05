package org.example.service;

import org.example.entity.User;

public interface UserService {
    String signUp(User user);
    User signIn(String username, String password);
}
