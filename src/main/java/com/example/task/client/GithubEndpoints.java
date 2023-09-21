package com.example.task.client;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubEndpoints {

    @GET("/users/{username}")
    Call<GithubUserResponse> getUser(@Path("username") String username);
}
