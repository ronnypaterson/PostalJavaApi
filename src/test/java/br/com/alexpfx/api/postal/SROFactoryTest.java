package br.com.alexpfx.api.postal;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SroFactoryTest {

    private SroFactory factory;
    @Before
    public void setUp () {
        factory = new SroFactory();
    }


    @Test(expected = SroInvalidoException.class)
    public void testCriar_tanhoInvalido() throws Exception {
        Sro sro = factory.criar("");
        fail ("excecao esperada");
    }


    @Test(expected = SroInvalidoException.class)
    public void testCriar_tipoServicoInvalido (){
        Sro sro = factory.criar("BB123456789BR");
        fail ("excecao esperada");
    }

    @Test(expected = SroInvalidoException.class)
    public void testCriar_digitoVerificadorNaoNumero (){
        Sro sro = factory.criar("DM12345678xBR");
        fail ("excecao esperada");
    }



    @Test
    public void testCriar_preValidado(){
        Sro sro = factory.criar("DM123456789BR");
        assertNotNull(sro);
        assertEquals(sro.getCodigoServico(), TipoSro.DM);
        assertEquals(sro.getNumero().intValue(), 12345678);
        assertEquals(sro.getDigitoVerificador().intValue(), 9);
        assertEquals(sro.getPaisOrigem(),"BR");
    }


    @Test
    public void testCriar_criadoInvalido(){
        Sro sro = factory.criar("DM123456789BR");
        assertFalse(sro.isValid());
    }

    @Test
    public void testCriar_variosObjetosValidos(){
        Sro sro = null;
        sro = factory.criar("DF976813565BR");
        assertTrue(sro.isValid());

        sro = factory.criar("DM180638790BR");
        assertTrue(sro.isValid());
        sro = factory.criar("DM180464317BR");
        assertTrue(sro.isValid());

    }








}