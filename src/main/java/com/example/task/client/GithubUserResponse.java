package com.example.task.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GithubUserResponse(long id,
                                 String login,
                                 String name,
                                 String type,
                                 String avatar_url,
                                 String created_at,
                                 long followers,
                                 long public_repos) {

}
