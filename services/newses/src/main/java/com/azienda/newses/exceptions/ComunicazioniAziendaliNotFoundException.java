package com.azienda.newses.exceptions;

public class ComunicazioniAziendaliNotFoundException extends RuntimeException
{
    private String message;

    public ComunicazioniAziendaliNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
