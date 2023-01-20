package com.api.management.service;

import com.api.management.exception.UserNotFoundExeption;
import com.api.management.model.User;
import com.api.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

    @Override
    public User fetchUser(String name, String password) {
        User user = userRepository.getUserByEmailAndPassword(name, password);
        if(user == null) {
            throw new UserNotFoundExeption("Usuário não encontrado");
        }

        return user;
    }
}
