package com.artbridge.exhibition.domain.exception;

public class CurrentUserLoginNotAvailableException extends RuntimeException {
    public CurrentUserLoginNotAvailableException() {
        super("Current user login is not available.");
    }
}
