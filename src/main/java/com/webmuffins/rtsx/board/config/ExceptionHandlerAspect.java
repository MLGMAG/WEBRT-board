package com.webmuffins.rtsx.board.config;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.webmuffins.rtsx.board.exception.InvalidTokenException;
import com.webmuffins.rtsx.board.exception.NotFoundException;

@RestControllerAdvice
public class ExceptionHandlerAspect extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(DataIntegrityViolationException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleInvalidTokenException(InvalidTokenException e) {
        return e.getMessage();
    }

}
