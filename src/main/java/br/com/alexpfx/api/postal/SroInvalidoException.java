package br.com.alexpfx.api.postal;

/**
 * Created by alex on 22/02/2015.
 */
public class SroInvalidoException extends RuntimeException{
    public SroInvalidoException(String message){
        super ("SRO Invalido: "+message);
    }

}
