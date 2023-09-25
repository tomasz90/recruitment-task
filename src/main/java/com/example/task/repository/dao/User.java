package com.example.task.repository.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "github_user")
@Getter
@Setter
public final class User {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private long requestCount;

    public User() {}

    public User(String login) {
        this.login = login;
        this.requestCount = 1;
    }
}
