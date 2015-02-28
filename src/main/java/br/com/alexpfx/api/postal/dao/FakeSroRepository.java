package br.com.alexpfx.api.postal.dao;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;

import java.util.*;

/**
 * Created by alex on 26/02/2015.
 */
public class FakeSroRepository implements SroRepository {
    private Map<Sro, List<SroRetornoInfo>> map;


    public FakeSroRepository() {
        this.map = new HashMap<>();
        map.put(new SroFactory().criar("DM180464317BR"), criarLista());
        map.put(new SroFactory().criar("DM180638790BR"), criarLista());
    }

    private List<SroRetornoInfo> criarLista() {
        List<SroRetornoInfo> lista = new ArrayList<>();
        lista.add(new SroRetornoInfo(new Date(), "CTE CAMPINAS/GCCAP - Valinhos/SP", "postado", "-"));
        lista.add(new SroRetornoInfo(new Date(), "CTE CAMPINAS/GCCAP - Valinhos/SP", "encaminhado", "Encaminhado para CTE CAMPINAS - Valinhos/SP"));
        return lista;
    }

    @Override
    public List<SroRetornoInfo> consultarSro(Sro sro) throws InfraException {
        List<SroRetornoInfo> retorno = map.get(sro);
        return retorno;
    }
}
