package com.stc.advice.error;

public class SpaceAlreadyExistsException extends RuntimeException {

    public SpaceAlreadyExistsException() {

        super("Space already exists");
    }
}
