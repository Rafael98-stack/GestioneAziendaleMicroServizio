package com.azienda.dipendenti.exceptions;

public class DipendenteNotFoundException extends RuntimeException {

    private String message;

    public DipendenteNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
