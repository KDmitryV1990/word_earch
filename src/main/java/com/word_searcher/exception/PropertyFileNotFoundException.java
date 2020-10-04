package com.word_searcher.exception;

public class PropertyFileNotFoundException extends Exception {

    public PropertyFileNotFoundException(String message) {
        super(message);
    }

    public PropertyFileNotFoundException(Throwable cause) {
        super(cause);
    }
}
