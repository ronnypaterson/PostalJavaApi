package br.com.alexpfx.api.postal;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;

public class SROFactory implements SroFactoryInterface{

    private static final int TAMANHO_PADRAO = 13;


    private String filtrarBrancos (String s){
        return CharMatcher.WHITESPACE.removeFrom(s);
    }
    private SRO criarSROValidado(String sro) {
        String sroSemBrancos = filtrarBrancos(sro);
        validarTamanho(sroSemBrancos);
        TipoSRO tipo = obterTipoServico(sroSemBrancos);
        Integer numero = obterNumero(sroSemBrancos);
        Integer dv = obterDv(sroSemBrancos);
        String pais = obterPais(sroSemBrancos);
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

    private Integer obterDv(String sro) {
        try {
            return Integer.valueOf(sro.substring(10, 11));
        } catch (NumberFormatException e) {
            throw new SROInvalidoException("numero com formato invalido");
        }
    }

    ///
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

    @Override
    public SRO criar(String codigoRastreamento) throws SROInvalidoException{
        return criarSROValidado(codigoRastreamento);
    }

    @Override
    public List<SRO> criarListaDescartarInvalidos(String codigosRastreamento) throws NenhumSroValidoException{
        String codigosSemBrancos = filtrarBrancos(codigosRastreamento);
        SROFactory factory = new SROFactory();
        Iterable<String> splitted = Splitter.fixedLength(TAMANHO_PADRAO).omitEmptyStrings().split(codigosSemBrancos);
        List<SRO> lista = new ArrayList<>();
        for (String s : splitted) {
            try {
                SRO criado = factory.criar(s);
                lista.add(criado);
            } catch (SROInvalidoException e) {
                //descartando invalidos
            }
        }
        if (lista.isEmpty()){
            throw new NenhumSroValidoException("Não encontrados SROs válidos na String fornecida: "+codigosRastreamento);
        }
        return lista;
    }


}
