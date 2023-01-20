package com.api.management.controller;

import com.api.management.model.User;
import com.api.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.fetchUserList();
    }

    @PostMapping("/{name}/{password}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("name") String name, @PathVariable("password") String password) {
        return userService.fetchUser(name, password);
    }
}