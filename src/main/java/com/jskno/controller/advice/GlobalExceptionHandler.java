package com.jskno.controller.advice;

import com.jskno.exceptions.EntityConstraintViolationException;
import com.jskno.service.ValidationErrorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @Autowired
    private ValidationErrorsService validationErrorsService;

    @ExceptionHandler(EntityConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(EntityConstraintViolationException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(ex.getConstraintRule(), ex.getMessage());
        return map;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(ConstraintViolationException ex) {
        return validationErrorsService.mapErrorsToMap(ex.getConstraintViolations());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(MethodArgumentNotValidException ex) {
        return validationErrorsService.mapErrorsToMap(ex.getBindingResult());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(MethodArgumentTypeMismatchException ex) {
        return validationErrorsService.mapErrorsToMap(ex);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handle(DataIntegrityViolationException ex) {
        Map<String, String> map = new HashMap<>();
        map.put(ex.getLocalizedMessage(), ex.getMessage());
        return map;
    }
}
