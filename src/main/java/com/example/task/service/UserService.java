package com.example.task.service;

import com.example.task.client.GithubApi;
import com.example.task.client.GithubUserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final GithubApi githubApi;
    public UserService(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    public UserInternalResponse getUser(String username) {
        GithubUserResponse githubUserResponse = githubApi.getUser(username);
        Double calculations = 6 / (double) githubUserResponse.followers() * (2 + githubUserResponse.public_repos());
        return UserInternalResponse.convert(githubUserResponse, calculations);
    }
}
