package com.agrawroh.selemize.exceptions;

/**
 * Parser Exception
 * 
 * @author agraw_ds7m
 * @version 2016-11-20 v1.0
 */
public class ParserException extends Exception {

    private static final long serialVersionUID = 5300113114429631111L;

    public ParserException(String message) {
        super(message);
    }

    public ParserException(Throwable cause) {
        super(cause);
    }

    public ParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParserException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
