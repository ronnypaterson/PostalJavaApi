package br.com.alexpfx.api.postal;

import java.util.List;

/**
 * Created by alexandre on 05/04/15.
 */
public interface SroFactoryInterface {

    /**
     * Cria uma Sro v�lida a partir de um c�digo de rastreamento. Se o c�digo for inv�lido, lan�a uma SroInvalidoException.
     *
     * @param codigoRastreamento
     * @return
     * @throws SROInvalidoException
     */
    SRO criar(String codigoRastreamento) throws SROInvalidoException;


    /**
     * Cria uma lista de Sros a partir de string que pode representar v�rios c�digos de rastremaneto. Caso neste string n�o haja
     * nenhum c�digo v�lido, lan�a uma NenhumSroValidoException.
     *
     * @param codigosRastreamento
     * @return
     * @throws NenhumSroValidoException
     */
    List<SRO> criarListaDescartarInvalidos(String codigosRastreamento) throws NenhumSroValidoException;

}
