package br.com.alexpfx.api.postal;

public class SROFactory {

    private static final int TAMANHO_PADRAO = 13;

    private SRO criarSROValidado(String sro) {
        validarTamanho(sro);
        TipoSRO tipo = obterTipoServico(sro);
        Integer numero = obterNumero(sro);
        Integer dv = obterDv(sro);
        String pais = obterPais(sro);
        return new SRO(tipo, numero, dv, pais);
    }



    private String obterPais(String sro) {
        return sro.substring(11, 13);
    }


    private TipoSRO obterTipoServico(String sro) {
        TipoSRO tipo = TipoSRO.obterPorCodigo(sro.substring(0, 2));
        if (tipo == null) {
            throw new SROInvalidoException("codigo de servico invalido");
        }
        return tipo;
    }

    private Integer obterDv (String sro){
        try {
            return Integer.valueOf(sro.substring(10, 11));
        } catch (NumberFormatException e) {
            throw new SROInvalidoException("numero com formato invalido");
        }
    }
    private Integer obterNumero(String sro) {
        try {
            return Integer.valueOf(sro.substring(2, 10));
        } catch (NumberFormatException e) {
            throw new SROInvalidoException("numero com formato invalido");
        }
    }

    private void validarTamanho(String sro) {
        if (sro == null || sro.length() != TAMANHO_PADRAO)
            throw new SROInvalidoException("tamanho string sro invalida");
    }

    public SRO criar(String codigoServico) {
        return criarSROValidado(codigoServico);
    }



}
