package com.accenture.challenge.utils.exceptions;

public class ServiceException extends BatchException {
    public ServiceException() {
        super();
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }
}
