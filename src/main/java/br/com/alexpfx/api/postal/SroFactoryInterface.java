package br.com.alexpfx.api.postal;

import java.util.List;

/**
 * Created by alexandre on 05/04/15.
 */
public interface SroFactoryInterface {

    /**
     * Cria uma Sro válida a partir de um código de rastreamento. Se o código for inválido, lança uma SroInvalidoException.
     *
     * @param codigoRastreamento
     * @return
     * @throws SroInvalidoException
     */
    Sro criar(String codigoRastreamento) throws SroInvalidoException;


    /**
     * Cria uma lista de Sros a partir de string que pode representar vários códigos de rastremaneto. Caso neste string não haja
     * nenhum código válido, lança uma NenhumSroValidoException.
     *
     * @param codigosRastreamento
     * @return
     * @throws NenhumSroValidoException
     */
    List<Sro> criarListaDescartarInvalidos(String codigosRastreamento) throws NenhumSroValidoException;

}
