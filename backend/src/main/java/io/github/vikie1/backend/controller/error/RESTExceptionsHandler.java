package io.github.vikie1.backend.controller.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RESTExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ErrorEntity> handleAll(ResponseStatusException ex, WebRequest request) {
        ErrorEntity apiError = new ErrorEntity(
                ex.getStatus(),
                ex.getRawStatusCode(),
                ex.getReason(),
                ex.getLocalizedMessage()
        );
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.status());
    }
}