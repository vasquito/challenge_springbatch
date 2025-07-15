package com.accenture.challenge.utils.exceptions;


import com.accenture.challenge.utils.exceptions.helper.ExceptionHelper;

public class BatchException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private long idReference;
    public long getIdReference() { return idReference; }
    public String getRefMessage() { return ExceptionHelper.generateMessage(idReference, getMessage()); }

    public BatchException() {
        super();
        idReference = ExceptionHelper.generateIdReference();
    }
    public BatchException(String message, Throwable cause) {
        super(message, cause);
        idReference = ExceptionHelper.generateIdReference();
    }
    public BatchException(String message) {
        super(message);
        idReference = ExceptionHelper.generateIdReference();
    }
    public BatchException(Throwable cause) {
        super(cause);
        idReference = ExceptionHelper.generateIdReference();
    }



}
