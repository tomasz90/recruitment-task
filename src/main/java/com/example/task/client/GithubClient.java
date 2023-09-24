package com.example.task.client;

import com.example.task.exception.UserExceptions.ExternalUserServiceNotAvailable;
import com.example.task.exception.UserExceptions.UserNotFoundException;
import com.example.task.exception.UserExceptions.UserServiceGenericException;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

@Component
final public class GithubClient {

    private final GithubEndpoints githubEndpoints;
    private final Logger logger = LoggerFactory.getLogger(GithubClient.class);

    public GithubClient(@Value("${githubUrl}") String githubUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        var retrofit = new Retrofit.Builder()
                .baseUrl(githubUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(httpClient.build())
                .build();

        githubEndpoints = retrofit.create(GithubEndpoints.class);
    }

    public GithubUserResponse getUser(String login) {
        var callUser = githubEndpoints.getUser(login);

        try {
            var response = callUser.execute();

            if (response.isSuccessful()) {
                return response.body();
            } else if (response.code() == 404) {
                logger.error("User not found");
                throw new UserNotFoundException();
            } else {
                assert response.errorBody() != null;
                var errorMessage = (response.errorBody()).string();
                logger.error("External service returned some error: " + errorMessage);
                throw new UserServiceGenericException();
            }
        } catch (IOException ex) {
            logger.error("External service not available");
            throw new ExternalUserServiceNotAvailable();
        }
    }
}
