package org.neurogine.exception;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.neurogine.utils.Constants;
import org.neurogine.utils.ResponseBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;


@Slf4j
@ControllerAdvice(basePackages = "org.neurogine.controller")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<JSONObject> handleResourceNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(ResponseBuilder.error((ex.getMessage() + " " + Constants.RESOURCE_NOT_FOUND)).getJson(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<JSONObject> handleConstraintViolation(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ResponseBuilder.error((handleExceptionInternal(
                ex, ex.getMessage(),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request))).getJson(),
                HttpStatus.BAD_REQUEST);
    }
}