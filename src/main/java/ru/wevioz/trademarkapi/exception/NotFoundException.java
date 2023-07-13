package ru.wevioz.trademarkapi.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String field) {
        super("The value in the field: " + field + " not found");
    }
}
