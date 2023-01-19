package com.api.management.service;

import com.api.management.model.User;

import java.util.List;

public interface UserService {

    List<User> fetchUserList();
    User fetchUser(Long id);
}
