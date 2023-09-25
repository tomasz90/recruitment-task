package com.example.task.exception;

import com.example.task.exception.UserExceptions.ExternalUserServiceNotAvailable;
import com.example.task.exception.UserExceptions.UserNotFoundException;
import com.example.task.exception.UserExceptions.UserServiceGenericException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
final class UserControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class})
    private ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {
        var responseBody = new JSONObject().put("message", ex.getMessage()).toString();
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {UserServiceGenericException.class})
    private ResponseEntity<Object> handleUserGenericException(RuntimeException ex, WebRequest request) {
        var responseBody = new JSONObject().put("message", ex.getMessage()).toString();
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = {ExternalUserServiceNotAvailable.class})
    private ResponseEntity<Object> handleExternalUserServiceNotAvailableException(RuntimeException ex, WebRequest request) {
        var responseBody = new JSONObject().put("message", ex.getMessage()).toString();
        return handleExceptionInternal(ex, responseBody, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}

