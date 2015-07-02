package br.com.alexpfx.api.postal.dao;

import br.com.alexpfx.api.postal.SROFactory;

import java.util.*;

/**
 * Created by alex on 26/02/2015.
 */
public class FakeSroRepository implements SroRepository {
    private Map<String, List<SroRetornoInfo>> map;


    public FakeSroRepository() {
        this.map = new HashMap<>();
        map.put(new SROFactory().criar("DM180464317BR").toString(), criarLista());
        map.put(new SROFactory().criar("DM180638790BR").toString(), criarLista());
    }

    private List<SroRetornoInfo> criarLista() {
        List<SroRetornoInfo> lista = new ArrayList<>();
        lista.add(new SroRetornoInfo(new Date(), "CTE CAMPINAS/GCCAP - Valinhos/SP", "postado", "-"));
        lista.add(new SroRetornoInfo(new Date(), "CTE CAMPINAS/GCCAP - Valinhos/SP", "encaminhado", "Encaminhado para CTE CAMPINAS - Valinhos/SP"));
        return lista;
    }

    @Override
    public List<SroRetornoInfo> consultarSro(String sro) throws InfraException {
        List<SroRetornoInfo> retorno = map.get(sro);
        return retorno;
    }
}
