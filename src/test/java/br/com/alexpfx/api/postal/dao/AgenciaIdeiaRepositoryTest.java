package br.com.alexpfx.api.postal.dao;

import br.com.alexpfx.api.postal.Sro;
import br.com.alexpfx.api.postal.SroFactory;
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
        Sro sro = new SroFactory().criar("SS123456789BR");
        repository.consultarSro(sro);
    }

    @Test(expected = InfraException.class)
    public void testConsultarSro_ObjetoNaoExiste() throws Exception {
        Sro sro = new SroFactory().criar("SS123101121BR");
        repository.consultarSro(sro);
    }

    @Test(expected = InfraException.class)
    public void testConsultarSro_EsperandoTimeout() throws Exception {
        repository = new AgenciaIdeiaRepository();
        repository.setTimeout(100);
        Sro sro = new SroFactory().criar("SS123456789BR");
        repository.consultarSro(sro);
    }

    @After
    public void tearDown() {
        repository = null;
    }

}