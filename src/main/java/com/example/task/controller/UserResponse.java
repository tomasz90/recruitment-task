package com.example.task.controller;

import com.example.task.client.GithubUserResponse;

public record UserResponse(long id,
                           String login,
                           String name,
                           String type,
                           String avatarUrl,
                           String createdAt,
                           Double calculations) {

    public static UserResponse convert(GithubUserResponse githubUserResponse, Double calculations) {
        return new UserResponse(githubUserResponse.id(),
                githubUserResponse.login(),
                githubUserResponse.name(),
                githubUserResponse.type(),
                githubUserResponse.avatar_url(),
                githubUserResponse.created_at(),
                calculations);
    }
}
