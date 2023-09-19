package com.example.task.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserService {


    @GetMapping("/users/{login}")
    UserResponse getUser(@PathVariable String login) {
        return null; // TODO: 19/09/2023  tbd
    }
}
