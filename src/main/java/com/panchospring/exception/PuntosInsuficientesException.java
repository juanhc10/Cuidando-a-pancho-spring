package com.panchospring.exception;

public class PuntosInsuficientesException extends IllegalArgumentException{
    public PuntosInsuficientesException() {
    }

    public PuntosInsuficientesException(String s) {
        super(s);
    }

    public PuntosInsuficientesException(String message, Throwable cause) {
        super(message, cause);
    }

    public PuntosInsuficientesException(Throwable cause) {
        super(cause);
    }
}
