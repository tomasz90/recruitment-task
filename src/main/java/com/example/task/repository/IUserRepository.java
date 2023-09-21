package com.example.task.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    @Modifying
    @Transactional
    @Query(value = "UPDATE github_user SET request_count = request_count + 1 WHERE login = :login",
            nativeQuery = true)
    void incrementUserRequestCount(@Param("login") String login);
}
