package br.com.alexpfx.api.postal;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;

public class SroFactory {

    private static final int TAMANHO_PADRAO = 13;


    private String filtrarBrancos (String s){
        return CharMatcher.WHITESPACE.removeFrom(s);
    }
    private Sro criarSROValidado(String sro) {
        String sroSemBrancos = filtrarBrancos(sro);
        validarTamanho(sroSemBrancos);
        TipoSro tipo = obterTipoServico(sroSemBrancos);
        Integer numero = obterNumero(sroSemBrancos);
        Integer dv = obterDv(sroSemBrancos);
        String pais = obterPais(sroSemBrancos);
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

    private Integer obterDv(String sro) {
        try {
            return Integer.valueOf(sro.substring(10, 11));
        } catch (NumberFormatException e) {
            throw new SroInvalidoException("numero com formato invalido");
        }
    }

    ///
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

    public Sro criar(String codigoRastreamento) {
        return criarSROValidado(codigoRastreamento);
    }

    public List<Sro> criarListaDescartarInvalidos(String codigosRastreamento) {
        String codigosSemBrancos = filtrarBrancos(codigosRastreamento);
        SroFactory factory = new SroFactory();
        Iterable<String> splitted = Splitter.fixedLength(TAMANHO_PADRAO).omitEmptyStrings().split(codigosSemBrancos);
        List<Sro> lista = new ArrayList<>();
        for (String s : splitted) {
            try {
                Sro criado = factory.criar(s);
                lista.add(criado);
            } catch (SroInvalidoException e) {
                System.out.println(s);
                //logar
            }
        }
        return lista;
    }


}
