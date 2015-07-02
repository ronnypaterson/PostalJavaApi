package br.com.alexpfx.api.postal;

/**
 * Created by alex on 22/02/2015.
 */
public class SROInvalidoException extends RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public SROInvalidoException(){
        super ("SRO Invalido");
    }
    public SROInvalidoException(String message){
        super ("SRO Invalido: "+message);
    }

}
