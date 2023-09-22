package com.example.task.controller;

import com.example.task.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{login}")
    public UserResponse getUser(@PathVariable String login) {
        return userService.getUser(login);
    }
}
