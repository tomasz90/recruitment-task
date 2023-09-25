package com.example.task.exception;

public final class UserExceptions {

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException() {
            super("User not found");
        }
    }

    public static class UserServiceGenericException extends RuntimeException {
        public UserServiceGenericException() {
            super("Internal server error");
        }
    }

    public static class ExternalUserServiceNotAvailable extends RuntimeException {
        public ExternalUserServiceNotAvailable() {
            super("External service not available");
        }
    }
}

