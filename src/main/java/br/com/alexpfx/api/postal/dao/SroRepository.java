package br.com.alexpfx.api.postal.dao;

import java.util.List;

/**
 * Created by alexandre on 28/02/15.
 */
public interface SroRepository {
    public List<SroRetornoInfo> consultarSro(String sro) throws InfraException;

}
