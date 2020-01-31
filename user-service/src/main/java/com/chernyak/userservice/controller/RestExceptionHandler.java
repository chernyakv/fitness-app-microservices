package com.chernyak.userservice.controller;

import com.chernyak.userservice.controller.error.ApiError;
import com.chernyak.userservice.exception.EntityNotFoundException;
import com.chernyak.userservice.exception.UserValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.NoSuchFileException;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserValidationException.class)
    protected ResponseEntity<Object> handleUserValidationException(final UserValidationException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST, ex);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(NoSuchFileException.class)
    protected ResponseEntity<Object> handleExpiredJwtException(NoSuchFileException e) {
        ApiError apiError = new ApiError(NOT_FOUND);
        apiError.setMessage("File was not found.");
        apiError.setDebugMessage(e.getLocalizedMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException e) {
        ApiError apiError = new ApiError(NOT_FOUND, e.getMessage(), e);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    public static String createExceptionMessage(List<ObjectError> errors) {
        StringBuilder builder = new StringBuilder();
        errors.forEach((error) -> {
            FieldError err = (FieldError) error;
            builder.append("Field - ")
                    .append(err.getField())
                    .append(" : ")
                    .append(err.getDefaultMessage())
                    .append("\n");
        });
        return builder.toString();
    }
}
