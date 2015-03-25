package br.com.alexpfx.api.postal;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SroFactoryTest {

    private SroFactory factory;
    @Before
    public void setUp () {
        factory = new SroFactory();
    }


    @Test(expected = SroInvalidoException.class)
    public void testCriar_tamanhoInvalido() throws Exception {
        Sro sro = factory.criar("");
        fail ("excecao esperada");
    }

    @Test(expected = SroInvalidoException.class)
    public void testCriar_capturarExcecao() throws Exception {
        try {
            Sro sro = factory.criar("");
            fail ("excecao esperada");
        } catch (SroInvalidoException e) {
            throw e;
        }
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
    public void testCriar_preValidado_comEspacos(){
        Sro sro = factory.criar("DM1 2345 678 9BR");
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

    @Test
    public void testTriarListaDescartarInvalidos_criar60todosValidos(){
        String cds = "DF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BR";
        List<Sro> sros = factory.criarListaDescartarInvalidos(cds);
        assertEquals(sros.size(), 60);
    }

    @Test
    public void testTriarListaDescartarInvalidos_criar60_invalidosNoFinal(){
        String cds = "DF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRBR123BRABCDFFAV161af1a6fa";
        List<Sro> sros = factory.criarListaDescartarInvalidos(cds);
        assertEquals(sros.size(), 60);

    }

    @Test
    public void testTriarListaDescartarInvalidos_criar60_invalidosNoFinal_comBrancos(){
        String cds = "DF9768 13565BRD M 180638790BR  DM18046 4317BRDF97 68135 65BRDM18 0638790BRDM 180464317BRD F976813565 BRDM1806387 90BRDM1804 64317 BRDF976 813565B RDM1806 38790BRDM18 0464317BRD F976813565B RDM180638 790BRDM1 8046 4317BRDF 976813565BRD M1  80638790BRDM18 0464317BRDF976 813565BR DM180638790BRDM180 464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF97681356 5BRDM180638790BRDM180464317BRDF976 813565BRD M180638790BRDM180464317BRBR123BRABC D F FAV161af1a6fa";
        List<Sro> sros = factory.criarListaDescartarInvalidos(cds);
        assertEquals(sros.size(), 60);
    }











}