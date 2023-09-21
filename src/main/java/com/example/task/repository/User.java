package com.example.task.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "github_user")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    long id;
    @Column(nullable = false)
    String login;
    @Column(nullable = false)
    long requestCount;

    public User() {}

    public User(String login) {
        this.login = login;
        this.requestCount = 0;
    }
}
