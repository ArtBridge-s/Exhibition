package com.artbridge.exhibition.domain.exception;

import com.artbridge.exhibition.web.errors.BadRequestAlertException;

public class CurrentTokenNotAvailableException extends BadRequestAlertException {

    public CurrentTokenNotAvailableException(String defaultMessage, String entityName, String errorKey) {
        super(defaultMessage, entityName, errorKey);
    }
}
