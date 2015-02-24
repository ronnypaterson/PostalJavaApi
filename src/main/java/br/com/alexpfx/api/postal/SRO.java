package br.com.alexpfx.api.postal;

/**
 * Created by alex on 22/02/2015.
 */
public class SRO {
    private final TipoSRO codigoServico;
    private final Integer numero;
    private final Integer digitoVerificador;
    private final String paisOrigem;

    SRO(TipoSRO codigoServico, Integer numero, Integer digitoVerificador, String paisOrigem) {
        this.codigoServico = codigoServico;
        this.numero = numero;
        this.paisOrigem = paisOrigem;
        this.digitoVerificador = digitoVerificador;
    }

    public void validarDigitoVerificador() throws SROInvalidoException{



    }

    public TipoSRO getCodigoServico() {
        return codigoServico;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }
}
