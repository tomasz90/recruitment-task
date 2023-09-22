package com.example.task.repository;

import com.example.task.repository.dao.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryUserRepository implements AbstractUserRepository {

    Map<Long, User> users = new HashMap<>();


    @Override
    public User findByLogin(String login) {
        var entry = users.entrySet()
                .stream()
                .filter(e -> e.getValue().getLogin().equals(login))
                .findFirst()
                .orElse(null);

        return (entry != null) ? entry.getValue() : null;
    }

    @Override
    public void incrementUserRequestCount(String login) {
        var user = Objects.requireNonNull(users.entrySet()
                        .stream()
                        .filter(e -> e.getValue().getLogin().equals(login))
                        .findFirst().orElse(null))
                .getValue();

        var requestCount = user.getRequestCount();
        user.setRequestCount(requestCount + 1);
    }
}
