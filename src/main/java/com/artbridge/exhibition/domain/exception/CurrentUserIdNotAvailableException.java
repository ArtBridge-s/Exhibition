package com.artbridge.exhibition.domain.exception;

public class CurrentUserIdNotAvailableException extends RuntimeException {
    public CurrentUserIdNotAvailableException() {
        super("Current user ID is not available.");
    }
}
