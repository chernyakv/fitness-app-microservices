package com.chernyak.authservice.controller;

import com.chernyak.authservice.exception.ItemNotFoundException;
import com.chernyak.authservice.exception.UserValidationException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.NoSuchFileException;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(ExpiredJwtException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserValidationException.class)
    protected ResponseEntity<ExceptionEntity> handleUserValidationException(final UserValidationException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchFileException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(NoSuchFileException e) {
        return new ResponseEntity<>(new ExceptionEntity("File is not found."), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(ItemNotFoundException e) {
        return new ResponseEntity<>(new ExceptionEntity(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    static class ExceptionEntity {
        private String message;

        ExceptionEntity(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static String createExceptionMessage(List<ObjectError> errors){
        StringBuilder builder = new StringBuilder();
        errors.forEach((error)->{
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
