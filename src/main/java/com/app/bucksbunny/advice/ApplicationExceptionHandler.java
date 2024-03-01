package com.app.bucksbunny.advice;

import com.app.bucksbunny.exceptions.DataIntegrationException;
import com.app.bucksbunny.exceptions.ResourceNotFoundException;
import com.app.bucksbunny.exceptions.UserNotFoundException;
import com.app.bucksbunny.response.ExceptionResponse;
import com.app.bucksbunny.util.ExceptionMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(
            Exception ex, WebRequest request) {

        String message = ex.getMessage() == null ? ExceptionMessage.USER_NOT_FOUND:ex.getMessage();

        ExceptionResponse response = new ExceptionResponse(message);

        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    protected ResponseEntity<Object> handleResourceNotFound( Exception ex, WebRequest request) {

        String message = ex.getMessage() == null ? ExceptionMessage.RESOURCE_NOT_FOUND:ex.getMessage();

        ExceptionResponse response = new ExceptionResponse(message);
        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ DataIntegrationException.class })
    protected ResponseEntity<Object> handleDataIntegration(DataIntegrityViolationException ex, WebRequest request) {

        ExceptionResponse response = new ExceptionResponse(ex.getMessage());

        return handleExceptionInternal(ex, response,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
