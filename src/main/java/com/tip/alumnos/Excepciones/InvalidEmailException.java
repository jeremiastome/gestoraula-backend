package com.tip.alumnos.Excepciones;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException(String msg) {
        super(msg);
    }
}