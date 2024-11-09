package com.codekion.vecino_seguro.exception;

public class NoTokenJwtException extends RuntimeException {

    public NoTokenJwtException(String message) {
        super(message);
    }
}
