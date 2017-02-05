package org.andy.javabrains.messenger.exception;

public class DataNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8828600637071105860L;

    public DataNotFoundException(String message) {
        super(message);
    }
}
