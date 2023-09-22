package com.example.task.repository;

import com.example.task.repository.dao.User;

public interface UserRepository {

    User save(User user);

    User findByLogin(String login);
    void incrementUserRequestCount(String login);
}
