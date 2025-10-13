package com.panchospring.exception;

public class MascotaFavoritaException extends IllegalArgumentException{
    public MascotaFavoritaException() {
    }

    public MascotaFavoritaException(String s) {
        super(s);
    }

    public MascotaFavoritaException(String message, Throwable cause) {
        super(message, cause);
    }

    public MascotaFavoritaException(Throwable cause) {
        super(cause);
    }
}
