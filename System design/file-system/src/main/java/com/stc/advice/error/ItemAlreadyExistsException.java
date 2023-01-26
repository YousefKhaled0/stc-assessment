package com.stc.advice.error;

public class ItemAlreadyExistsException extends RuntimeException {

    public ItemAlreadyExistsException() {

        super("Item already exists");
    }
}
