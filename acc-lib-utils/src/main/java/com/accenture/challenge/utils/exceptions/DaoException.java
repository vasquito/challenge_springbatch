package com.accenture.challenge.utils.exceptions;

public class DaoException extends BatchException {
    public DaoException() {
        super();
    }
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    public DaoException(String message) {
        super(message);
    }
    public DaoException(Throwable cause) {
        super(cause);
    }
}
