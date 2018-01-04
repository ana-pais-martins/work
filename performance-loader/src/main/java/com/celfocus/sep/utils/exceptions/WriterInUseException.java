package com.celfocus.sep.utils.exceptions;

public class WriterInUseException extends Exception {

    private static final long serialVersionUID = 1L;

    @Override
    public String getMessage() {
        return "Writer is already in use and cannot be overwritten";
    }
}
