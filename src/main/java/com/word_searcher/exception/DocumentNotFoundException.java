package com.word_searcher.exception;

public class DocumentNotFoundException extends Exception {

    public DocumentNotFoundException(String message) {
        super(message);
    }

    public DocumentNotFoundException(Throwable cause) {
        super(cause);
    }
}
