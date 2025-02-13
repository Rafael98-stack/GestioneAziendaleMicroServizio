package com.azienda.newses.exceptions;

public class NewsNotFoundException extends RuntimeException
{
    private String message;

    public NewsNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
