package com.devtiro.quickstart.exceptions;

public class NoEmptySpaceException extends RuntimeException {
    public NoEmptySpaceException(String message) {
        super(message);
    }
}
