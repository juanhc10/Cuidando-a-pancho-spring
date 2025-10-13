package com.panchospring.handler;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ErrorResponse extends ResponseEntity<Map<String, String>> {
    public ErrorResponse(Map<String, String> body, HttpStatusCode status) {
        super(body, status);
    }
}
