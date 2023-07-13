package ru.wevioz.trademarkapi.exception;

public class InvalidFileException extends Exception{
    public InvalidFileException() {
        super("File is not valid");
    }
}

