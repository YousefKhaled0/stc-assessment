package com.stc.advice;

import com.stc.advice.error.AuthException;
import com.stc.advice.error.ItemAlreadyExistsException;
import com.stc.advice.error.ItemNotFoundException;
import com.stc.advice.error.PermissionGroupNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> handleAuthException(AuthException ex, WebRequest request) {

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({
            ItemAlreadyExistsException.class,
            MultipartException.class
    })
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {


        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({
            ItemNotFoundException.class,
            PermissionGroupNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {


        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
