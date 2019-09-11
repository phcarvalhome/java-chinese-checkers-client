package com.phcarvalho.model.exception;

public class ConnectionException extends Exception {

    private String title;

    public ConnectionException(String message, String title) {
        super(message);
        this.title = title;
    }

    public ConnectionException(String message, Throwable cause, String title) {
        super(message, cause);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
