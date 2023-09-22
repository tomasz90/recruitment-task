package com.example.task.repository;

import com.example.task.repository.dao.User;

public interface AbstractUserRepository {

    User findByLogin(String login);
    void incrementUserRequestCount(String login);
}
