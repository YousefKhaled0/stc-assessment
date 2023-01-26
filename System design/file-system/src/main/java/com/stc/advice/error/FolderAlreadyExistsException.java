package com.stc.advice.error;

public class FolderAlreadyExistsException extends RuntimeException {

    public FolderAlreadyExistsException() {

        super("Folder already exists");
    }
}
