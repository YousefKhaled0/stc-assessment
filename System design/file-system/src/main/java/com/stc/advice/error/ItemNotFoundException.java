package com.stc.advice.error;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException() {

        super("Space Not Found");
    }
}
