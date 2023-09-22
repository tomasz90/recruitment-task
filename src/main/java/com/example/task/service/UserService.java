package com.example.task.service;

import com.example.task.client.GithubApi;
import com.example.task.client.GithubUserResponse;
import com.example.task.controller.UserResponse;
import com.example.task.exception.UserExceptions;
import com.example.task.repository.UserRepository;
import com.example.task.repository.dao.User;
import org.springframework.stereotype.Component;

@Component
public final class UserService {

    private final GithubApi githubApi;
    private final UserRepository userRepository;

    public UserService(GithubApi githubApi, UserRepository userRepository) {
        this.githubApi = githubApi;
        this.userRepository = userRepository;
    }

    public UserResponse getUser(String login) {

        var githubUserResponse = githubApi.getUser(login);
        var calculations = 6 / (double) githubUserResponse.followers() * (2 + githubUserResponse.public_repos());
        var user = userRepository.findByLogin(login);

        if(user == null) {
            userRepository.save(new User(login));
        } else {
            userRepository.incrementUserRequestCount(login);
        }
        return UserResponse.convert(githubUserResponse, calculations);
    }
}
