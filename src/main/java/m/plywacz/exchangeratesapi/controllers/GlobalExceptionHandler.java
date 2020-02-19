package m.plywacz.exchangeratesapi.controllers;
/*
Author: BeGieU
Date: 12.02.2020
*/

import m.plywacz.exchangeratesapi.exceptions.EntityDuplicateException;
import m.plywacz.exchangeratesapi.exceptions.IncorrectJsonInputException;
import m.plywacz.exchangeratesapi.exceptions.InvalidTokenException;
import m.plywacz.exchangeratesapi.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {EntityDuplicateException.class})
    public void handleAttemptToDuplicateEntity(EntityDuplicateException ex) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage(), ex);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public void handleAccessToNonExistingResource(ResourceNotFoundException ex) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getResourceName(), ex);
    }

    @ExceptionHandler(value = {IncorrectJsonInputException.class})
    public void handleInvalidJsonInput(IncorrectJsonInputException ex) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getInfo(), ex);
    }

    @ExceptionHandler(value = {InvalidTokenException.class})
    public void handleInvalidTokenHeader(InvalidTokenException ex) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
    }


}

