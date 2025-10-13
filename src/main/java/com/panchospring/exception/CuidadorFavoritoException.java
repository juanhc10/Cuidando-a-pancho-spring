package com.panchospring.exception;

import lombok.AllArgsConstructor;


public class CuidadorFavoritoException extends IllegalArgumentException{
    public CuidadorFavoritoException() {
    }

    public CuidadorFavoritoException(String s) {
        super(s);
    }

    public CuidadorFavoritoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CuidadorFavoritoException(Throwable cause) {
        super(cause);
    }
}
