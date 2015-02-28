package br.com.alexpfx.api.postal.dao;

import br.com.alexpfx.api.postal.Sro;

import java.util.List;

/**
 * Created by alexandre on 28/02/15.
 */
public interface SroRepository {
    public List<SroRetornoInfo> consultarSro(Sro sro) throws InfraException;

}
