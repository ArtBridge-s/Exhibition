package com.artbridge.exhibition.domain.exception;

import com.artbridge.exhibition.web.errors.BadRequestAlertException;

public class FileNotFoundException extends BadRequestAlertException {

    public FileNotFoundException(String defaultMessage, String entityName, String errorKey) {
        super(defaultMessage, entityName, errorKey);
    }
}
