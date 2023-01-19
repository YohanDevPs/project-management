package com.api.management.controller;

import com.api.management.model.User;
import com.api.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        return userService.fetchUserList();
    }
}
