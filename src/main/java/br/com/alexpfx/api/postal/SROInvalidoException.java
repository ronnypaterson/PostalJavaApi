package br.com.alexpfx.api.postal;

/**
 * Created by alex on 22/02/2015.
 */
public class SROInvalidoException extends RuntimeException{
    public SROInvalidoException (String message){
        super ("SRO Invalido: "+message);
    }

}
