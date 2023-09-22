package com.example.task.repository;

import com.example.task.repository.dao.User;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
@Profile("test")
class InMemoryUserRepository implements UserRepository {

    private final Map<Long, User> users = new HashMap<>();


    @Override
    public User save(User user) {
        users.put((long) users.size(), user);
        return user;
    }

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

    public void clean() {
        users.clear();
    }
}
