package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(0.0);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaPieninMahdollinenTilavuusOnNolla() {
        varasto = new Varasto(-2.5);
        assertEquals(0.0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    @Test
    public void uudellaVarastollaOikeaAlkusaldo() {
        varasto = new Varasto(2.5, 1.0);
        assertEquals(1.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void alkusaldollaEiVoiTallettaaYliTilavuudenVerran() {
        varasto = new Varasto(1.0, 2.5);
        assertEquals(1.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void negatiivinenAlkusaldoVastaaNollaa() {
        varasto = new Varasto(1.0, -0.5);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void alkusaldollaEiVoiTallettaaKelvottomaanVarastoon() {
        varasto = new Varasto(0.0, 20);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void varastoonEiVoiLisataNegatiivista() {
        varasto.lisaaVarastoon(-2.5);
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void varastoonEiVoiLisataYliTilavuuden() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void varastostaEiVoiOttaaYliSaldon() {
        varasto.lisaaVarastoon(5);
        double vert = varasto.otaVarastosta(10);
        assertEquals(5, vert, vertailuTarkkuus);
    }
    @Test
    public void varastostaEiVoiOttaaNegatiivistaMaaraa() {
        varasto.lisaaVarastoon(5);
        double vert = varasto.otaVarastosta(-2.5);
        assertEquals(0.0, vert, vertailuTarkkuus);
    }
    @Test
    public void varastoTulostaaOikein() {
        varasto.lisaaVarastoon(5);
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }
}
