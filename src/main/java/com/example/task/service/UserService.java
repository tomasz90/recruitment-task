package com.example.task.service;

import com.example.task.client.GithubApi;
import com.example.task.client.GithubUserResponse;
import com.example.task.controller.UserResponse;
import org.springframework.stereotype.Component;

@Component
public final class UserService {

    private final GithubApi githubApi;
    public UserService(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    public UserResponse getUser(String username) {
        GithubUserResponse githubUserResponse = githubApi.getUser(username);
        Double calculations = 6 / (double) githubUserResponse.followers() * (2 + githubUserResponse.public_repos());
        return UserResponse.convert(githubUserResponse, calculations);
    }
}
