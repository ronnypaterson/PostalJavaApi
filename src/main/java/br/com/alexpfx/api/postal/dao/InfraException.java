package br.com.alexpfx.api.postal.dao;


/**
 * Created by alex on 26/02/2015.
 */
public class InfraException extends RuntimeException {


    public InfraException() {
    }

    public InfraException(String message, Throwable cause) {
        super(message, cause);
    }
}
