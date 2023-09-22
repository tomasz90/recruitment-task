package com.example.task.repository;

import com.example.task.repository.dao.User;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
interface DatabaseUserRepository extends JpaRepository<User, Long>, UserRepository {

    @Modifying
    @Transactional
    @Query(value = "UPDATE github_user SET request_count = request_count + 1 WHERE login = :login",
            nativeQuery = true)
    void incrementUserRequestCount(@Param("login") String login);
}
