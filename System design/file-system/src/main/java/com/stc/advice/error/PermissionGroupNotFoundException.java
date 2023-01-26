package com.stc.advice.error;

public class PermissionGroupNotFoundException extends RuntimeException {

    public PermissionGroupNotFoundException() {

        super("Permission Group Not Found");
    }
}
