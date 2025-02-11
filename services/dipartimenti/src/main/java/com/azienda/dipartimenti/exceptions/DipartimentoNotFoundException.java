package com.azienda.dipartimenti.exceptions;

public class DipartimentoNotFoundException extends RuntimeException{
    private String message;

    public DipartimentoNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
