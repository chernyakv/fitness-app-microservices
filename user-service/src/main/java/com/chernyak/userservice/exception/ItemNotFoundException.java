package com.chernyak.userservice.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public ItemNotFoundException(String msg) {
        super(msg);
    }
}
