package com.example.task.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface GithubEndpoints {

    @GET("/users/{login}")
    Call<GithubUserResponse> getUser(@Path("login") String login);
}
