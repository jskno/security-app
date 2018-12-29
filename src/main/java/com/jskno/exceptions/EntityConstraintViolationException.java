package com.jskno.exceptions;

public class EntityConstraintViolationException extends RuntimeException {

    private String constraintRule;

    public EntityConstraintViolationException(String message, String constraintRule) {
        super(message);
        this.constraintRule = constraintRule;
    }

    public String getConstraintRule() {
        return constraintRule;
    }
}
