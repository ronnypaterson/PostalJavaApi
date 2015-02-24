package br.com.alexpfx.api.postal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SROFactoryTest {

    private SROFactory factory;
    @Before
    public void setUp () {
        factory = new SROFactory();
    }


    @Test(expected = SROInvalidoException.class)
    public void testCriar_tanhoInvalido() throws Exception {
        SRO sro = factory.criar("");
        fail ("excecao esperada");
    }

    @Test(expected = SROInvalidoException.class)
    public void testCriar_tipoServicoInvalido (){
        SRO sro = factory.criar("AS123456789BR");
        fail ("excecao esperada");
    }

    @Test(expected = SROInvalidoException.class)
    public void testCriar_digitoVerificadorNaoNumero (){
        SRO sro = factory.criar("DM12345678xBR");
        fail ("excecao esperada");
    }



    @Test
    public void testCriar_preValidado(){
        SRO sro = factory.criar("DM123456789BR");
        assertNotNull(sro);
        assertEquals(sro.getCodigoServico(), TipoSRO.DM);
        assertEquals(sro.getNumero().intValue(), 12345678);
        assertEquals(sro.getDigitoVerificador().intValue(), 9);
        assertEquals(sro.getPaisOrigem(),"BR");
    }


    @Test
    public void testCriar_criadoInvalido(){
        SRO sro = factory.criar("DM123456789BR");
        assertFalse(sro.isValid());
    }

    @Test
    public void testCriar_variosObjetosValidos(){
        SRO sro = null;
        sro = factory.criar("DF976813565BR");
        assertTrue(sro.isValid());

        sro = factory.criar("DM180638790BR");
        assertTrue(sro.isValid());
        sro = factory.criar("DM180464317BR");
        assertTrue(sro.isValid());

    }








}