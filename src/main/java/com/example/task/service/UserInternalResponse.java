package com.example.task.service;

import com.example.task.client.GithubUserResponse;

import java.util.Date;

public record UserInternalResponse(long id,
                                   String login,
                                   String name,
                                   String type,
                                   String avatarUrl,
                                   String createdAt,
                                   Double calculations) {

    public static UserInternalResponse convert(GithubUserResponse githubUserResponse, Double calculations) {
        return new UserInternalResponse(githubUserResponse.id(),
                githubUserResponse.login(),
                githubUserResponse.name(),
                githubUserResponse.type(),
                githubUserResponse.avatar_url(),
                githubUserResponse.created_at(),
                calculations);
    }
}
