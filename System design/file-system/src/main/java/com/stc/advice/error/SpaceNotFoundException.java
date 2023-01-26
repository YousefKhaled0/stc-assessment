package com.stc.advice.error;

public class SpaceNotFoundException extends RuntimeException {

    public SpaceNotFoundException() {

        super("Space Not Found");
    }
}
