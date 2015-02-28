package br.com.alexpfx.api.postal;

public class SroFactory {

    private static final int TAMANHO_PADRAO = 13;

    private Sro criarSROValidado(String sro) {
        validarTamanho(sro);
        TipoSro tipo = obterTipoServico(sro);
        Integer numero = obterNumero(sro);
        Integer dv = obterDv(sro);
        String pais = obterPais(sro);
        return new Sro(tipo, numero, dv, pais);
    }



    private String obterPais(String sro) {
        return sro.substring(11, 13);
    }


    private TipoSro obterTipoServico(String sro) {
        TipoSro tipo = TipoSro.obterPorCodigo(sro.substring(0, 2));
        if (tipo == null) {
            throw new SroInvalidoException("codigo de servico invalido");
        }
        return tipo;
    }

    private Integer obterDv (String sro){
        try {
            return Integer.valueOf(sro.substring(10, 11));
        } catch (NumberFormatException e) {
            throw new SroInvalidoException("numero com formato invalido");
        }
    }
    private Integer obterNumero(String sro) {
        try {
            return Integer.valueOf(sro.substring(2, 10));
        } catch (NumberFormatException e) {
            throw new SroInvalidoException("numero com formato invalido");
        }
    }

    private void validarTamanho(String sro) {
        if (sro == null || sro.length() != TAMANHO_PADRAO)
            throw new SroInvalidoException("tamanho string sro invalida");
    }

    public Sro criar(String codigoServico) {
        return criarSROValidado(codigoServico);
    }



}
