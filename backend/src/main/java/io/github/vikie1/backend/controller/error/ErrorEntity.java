package io.github.vikie1.backend.controller.error;

import org.springframework.http.HttpStatus;

public record ErrorEntity(HttpStatus status, int statusCode, String reason, String message) { }
