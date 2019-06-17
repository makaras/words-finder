package com.maciejkaras.wordsfinder.exception;

public class UnreadableFileException extends RuntimeException {

    public UnreadableFileException(String errorMessage) {
        super(errorMessage);
    }
}
