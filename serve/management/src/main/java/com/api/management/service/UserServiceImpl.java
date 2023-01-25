package com.api.management.service;

import com.api.management.exception.UserNotFoundExeption;
import com.api.management.model.User;
import com.api.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }


    @Override
    public User findEmailOrPassword(User user) {
        if(user.getPassword() == null || user.getName() == null) {
            throw new UserNotFoundExeption("Senha ou usuário em branco");
        }

        User fetchUser = userRepository.findUserByNameAndPassword(user.getName() , user.getPassword());
        if(fetchUser == null) {
            throw new UserNotFoundExeption("Usuário não encontrado");
        }

        return fetchUser;
    }

    @Override
    public void saveUser(User user) {
        if(user.getEmail() == null || user.getPassword() == null || user.getName() == null) {
            throw new RuntimeException();
        }
        userRepository.save(user);
    }
}
