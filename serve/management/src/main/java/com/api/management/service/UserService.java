package com.api.management.service;

import com.api.management.model.User;

import java.util.List;

public interface UserService {

    List<User> fetchUserList();
    User fetchUser(String name, String password);

    User findByEmail(String email);

    User findEmailOrPassword(User user);

    void saveUser(User user);
}
