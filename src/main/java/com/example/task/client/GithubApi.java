package com.example.task.client;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

@Component
final public class GithubApi {

    private final GithubEndpoints githubEndpoints;
    public GithubApi(GithubClient githubClient) {
        githubEndpoints = githubClient.getRetrofit().create(GithubEndpoints.class);
    }

    public GithubUserResponse getUser(String username) {
        GithubUserResponse user = null;
        try {
            Call<GithubUserResponse> callUser = githubEndpoints.getUser(username);
            Response<GithubUserResponse> response = callUser.execute();
            user = response.body();
        } catch (Exception ex) {

        }
        return user;
    }
}
