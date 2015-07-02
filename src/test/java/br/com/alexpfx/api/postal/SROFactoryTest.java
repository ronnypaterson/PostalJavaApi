package br.com.alexpfx.api.postal;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SROFactoryTest {

    private SroFactoryInterface factory;

    @Before
    public void setUp() {
        factory = new SROFactory();
    }

    @Test(expected = SROInvalidoException.class)
    public void testCriar_tamanhoInvalido() throws Exception {
        fail("excecao esperada");
    }

    @Test(expected = SROInvalidoException.class)
    public void testCriar_capturarExcecao() throws Exception {
        try {
            //SRO sro = factory.criar("");
            factory.criar("");
            fail("excecao esperada");
        } catch (SROInvalidoException e) {
            throw e;
        }
    }

    @Test(expected = SROInvalidoException.class)
    public void testCriar_tipoServicoInvalido() {
        //SRO sro = factory.criar("BB123456789BR");
    	factory.criar("BB123456789BR");
        fail("excecao esperada");
    }

    @Test(expected = SROInvalidoException.class)
    public void testCriar_digitoVerificadorNaoNumero() {
        //SRO sro = factory.criar("DM12345678xBR");
    	factory.criar("DM12345678xBR");
        fail("excecao esperada");
    }

    @Test
    public void testCriar_preValidado() {
        SRO sro = factory.criar("DM123456789BR");
        assertNotNull(sro);
        assertEquals(sro.getCodigoServico(), TipoSRO.DM);
        assertEquals(sro.getNumero().intValue(), 12345678);
        assertEquals(sro.getDigitoVerificador().intValue(), 9);
        assertEquals(sro.getPaisOrigem(), "BR");
    }

    @Test
    public void testCriar_preValidado_comEspacos() {
        SRO sro = factory.criar("DM1 2345 678 9BR");
        assertNotNull(sro);
        assertEquals(sro.getCodigoServico(), TipoSRO.DM);
        assertEquals(sro.getNumero().intValue(), 12345678);
        assertEquals(sro.getDigitoVerificador().intValue(), 9);
        assertEquals(sro.getPaisOrigem(), "BR");
    }

    @Test
    public void testCriar_criadoInvalido() {
        SRO sro = factory.criar("DM123456789BR");
        assertFalse(sro.isValid());
    }

    @Test
    public void testCriar_variosObjetosValidos() {
        SRO sro = null;
        sro = factory.criar("DF976813565BR");
        assertTrue(sro.isValid());

        sro = factory.criar("DM180638790BR");
        assertTrue(sro.isValid());
        sro = factory.criar("DM180464317BR");
        assertTrue(sro.isValid());

    }

    @Test
    public void testTriarListaDescartarInvalidos_criar60todosValidos() {
        String cds = "DF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BR";
        List<SRO> sros = factory.criarListaDescartarInvalidos(cds);
        assertEquals(sros.size(), 60);
    }

    @Test
    public void testTriarListaDescartarInvalidos_criar60_invalidosNoFinal() {
        String cds = "DF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRBR123BRABCDFFAV161af1a6fa";
        List<SRO> sros = factory.criarListaDescartarInvalidos(cds);
        assertEquals(sros.size(), 60);

    }

    @Test
    public void testTriarListaDescartarInvalidos_criar60_invalidosNoFinal_comBrancos() {
        String cds = "DF9768 13565BRD M 180638790BR  DM18046 4317BRDF97 68135 65BRDM18 0638790BRDM 180464317BRD F976813565 BRDM1806387 90BRDM1804 64317 BRDF976 813565B RDM1806 38790BRDM18 0464317BRD F976813565B RDM180638 790BRDM1 8046 4317BRDF 976813565BRD M1  80638790BRDM18 0464317BRDF976 813565BR DM180638790BRDM180 464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF976813565BRDM180638790BRDM180464317BRDF97681356 5BRDM180638790BRDM180464317BRDF976 813565BRD M180638790BRDM180464317BRBR123BRABC D F FAV161af1a6fa";
        List<SRO> sros = factory.criarListaDescartarInvalidos(cds);
        assertEquals(sros.size(), 60);
    }

    @Test(expected = NenhumSroValidoException.class)
    public void testTriarListaDescartarInvalidos_nenhumValido() {
        String cds = "fafa";
        //List<SRO> sros = factory.criarListaDescartarInvalidos(cds);        
        factory.criarListaDescartarInvalidos(cds);
    }

}