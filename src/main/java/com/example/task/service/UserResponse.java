package com.example.task.service;

import java.util.Date;

public record UserResponse(long id,
                           String login,
                           String name,
                           String type,
                           String avatarUrl,
                           Date createdAt,
                           Double calculations) {
}
