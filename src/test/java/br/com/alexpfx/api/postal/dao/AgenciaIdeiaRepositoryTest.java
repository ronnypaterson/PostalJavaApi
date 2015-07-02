package br.com.alexpfx.api.postal.dao;

import br.com.alexpfx.api.postal.SRO;
import br.com.alexpfx.api.postal.SROFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AgenciaIdeiaRepositoryTest {
    AgenciaIdeiaRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new AgenciaIdeiaRepository();
    }

    @Test
    public void testConsultarSro_ObjetoValido() throws Exception {
        SRO sro = new SROFactory().criar("SS123456789BR");
        repository.consultarSro(sro.toString());
    }

    @Test(expected = InfraException.class)
    public void testConsultarSro_ObjetoNaoExiste() throws Exception {
        SRO sro = new SROFactory().criar("SS123101121BR");
        repository.consultarSro(sro.toString());
    }

    @Test(expected = InfraException.class)
    public void testConsultarSro_EsperandoTimeout() throws Exception {
        repository = new AgenciaIdeiaRepository();
        repository.setTimeout(100);
        SRO sro = new SROFactory().criar("SS123456789BR");
        repository.consultarSro(sro.toString());
    }

    @After
    public void tearDown() {
        repository = null;
    }

}