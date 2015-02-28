package br.com.alexpfx.api.postal;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by alex on 22/02/2015.
 */
public class Sro {
    private final TipoSro codigoServico;
    private final Integer numero;
    private final Integer digitoVerificador;
    private final String paisOrigem;

    Sro(TipoSro codigoServico, Integer numero, Integer digitoVerificador, String paisOrigem) {
        this.codigoServico = codigoServico;
        this.numero = numero;
        this.paisOrigem = paisOrigem.toUpperCase();
        this.digitoVerificador = digitoVerificador;
    }
//x
    public boolean isValid() throws SroInvalidoException {
        return digitoVerificador.equals(calcularDv());
    }
    public int calcularDv (){
        int [] fatores = new int [] {8,6,4,2,3,5,9,7};
        int [] ia = toIntArray(getNumero(), 8);
        int soma = 0;
        for (int i = 0; i < fatores.length; i++){
            int m = fatores [i] * ia [i];
            soma += m;
        }
        int div = (soma % 11);
        if (div == 0) {
            return 5;
        }
        if (div == 1){
            return 0;
        }
        return 11 - div;
    }


    private int [] toIntArray (int n, int minSize){

        String s = StringUtils.leftPad(String.valueOf(n), minSize, '0');
        int c = 0;
        int [] ar = new int [s.length()];
        for (char ch:s.toCharArray()){
            ar [c++] = ch - '0';
        }
        return ar;
    }

    public Integer getDigitoVerificador() {
        return digitoVerificador;
    }

    public TipoSro getCodigoServico() {
        return codigoServico;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sro sro = (Sro) o;

        if (codigoServico != sro.codigoServico) return false;
        if (digitoVerificador != null ? !digitoVerificador.equals(sro.digitoVerificador) : sro.digitoVerificador != null)
            return false;
        if (numero != null ? !numero.equals(sro.numero) : sro.numero != null) return false;
        if (paisOrigem != null ? !paisOrigem.equals(sro.paisOrigem) : sro.paisOrigem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoServico != null ? codigoServico.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (digitoVerificador != null ? digitoVerificador.hashCode() : 0);
        result = 31 * result + (paisOrigem != null ? paisOrigem.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getCodigoServico().getCodigo()+getNumero()+getDigitoVerificador()+getPaisOrigem();
    }
}
