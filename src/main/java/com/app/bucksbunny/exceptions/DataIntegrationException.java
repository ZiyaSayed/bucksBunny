package com.app.bucksbunny.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DataIntegrationException extends DataIntegrityViolationException {
    public DataIntegrationException(String msg) {
        super(msg);
    }

    public DataIntegrationException(String msg, Throwable cause) {
        super(msg, cause);
    }


}
