package com.azienda.comunicazioni_aziendali.exceptions;

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
