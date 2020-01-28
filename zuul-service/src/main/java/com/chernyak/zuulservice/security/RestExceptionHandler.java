package com.chernyak.exerciseservice.controller;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<ExceptionEntity> handleExpiredJwtException(FeignException e) {
        return new ResponseEntity<>(new ExceptionEntity(new String(e.content())), HttpStatus.resolve(e.status()));
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
