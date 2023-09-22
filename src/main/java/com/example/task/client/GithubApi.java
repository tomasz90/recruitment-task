package com.example.task.client;

import com.example.task.exception.UserExceptions.ExternalUserServiceNotAvailable;
import com.example.task.exception.UserExceptions.UserNotFoundException;
import com.example.task.exception.UserExceptions.UserServiceGenericException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
final public class GithubApi {

    private final GithubEndpoints githubEndpoints;

    public GithubApi(GithubClient githubClient) {
        githubEndpoints = githubClient.getRetrofit().create(GithubEndpoints.class);
    }

    public GithubUserResponse getUser(String login) {
        var callUser = githubEndpoints.getUser(login);

        try {
            var response = callUser.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else if (response.code() == 404) {
                throw new UserNotFoundException();
            } else {
                assert response.errorBody() != null;
                var errorMessage = (response.errorBody()).string();
                throw new UserServiceGenericException(errorMessage);
            }
        } catch (IOException ex) {
            throw new ExternalUserServiceNotAvailable();
        }
    }
}
