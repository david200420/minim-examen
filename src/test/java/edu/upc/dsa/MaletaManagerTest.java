package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;
import edu.upc.dsa.exceptions.AvionNotFoundException;
import edu.upc.dsa.exceptions.VueloNotFoundException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Queue;

public class MaletaManagerTest {
    AvionesManager pm;

    @Before
    public void setUp() {

        pm = AvionesManagerImpl.getInstance();

        pm.addAvion("A1", "Boeing 737", "Iberia");
        pm.addAvion("A2", "Airbus A320", "Vueling");
        pm.addVuelo("V1", "A1", "Madrid", "Paris", "8:00", "10:00");
        pm.addVuelo("V2", "A2", "Paris", "Madrid", "10:00", "13:00");
    }

    @After
    public void tearDown() {

        this.pm = null;
    }

    @Test
    public void testAddAvion() {

        pm.addAvion("A1", "Boeing 747", "Lufthansa");
        Avion avion = pm.getAvion("A1");
        Assert.assertNotNull(avion);
        Assert.assertEquals("Boeing 747", avion.getModelo());
        Assert.assertEquals("Lufthansa", avion.getCompania());
    }

    @Test
    public void testModifyAvion() {
        pm.addAvion("A1", "Boeing 747", "Lufthansa");
        pm.addAvion("A1", "Boeing 777", "Iberia");
        Avion avion = pm.getAvion("A1");
        Assert.assertEquals("Boeing 777", avion.getModelo());
        Assert.assertEquals("Iberia", avion.getCompania());
    }

    @Test
    public void testAddVuelo() {

        pm.addVuelo("V3", "A1", "Argentina", "Marrakech", "10:00", "15:00");
        Vuelo vuelo = pm.getVuelo("V3");
        Assert.assertNotNull(vuelo);
        Assert.assertEquals("Argentina", vuelo.getOrigen());
        Assert.assertEquals("Marrakech", vuelo.getDestino());
        Assert.assertEquals("10:00", vuelo.getHoraSalida());
        Assert.assertEquals("15:00", vuelo.getHoraLlegada());
    }

    @Test
    public void testModifyVuelo() {

        pm.addVuelo("V1", "A1", "Barcelona", "Madrid", "11:00", "13:00");
        Vuelo vuelo = pm.getVuelo("V1");
        Assert.assertNotNull(vuelo);
        Assert.assertEquals("Barcelona", vuelo.getOrigen());
        Assert.assertEquals("Madrid", vuelo.getDestino());
        Assert.assertEquals("11:00", vuelo.getHoraSalida());
        Assert.assertEquals("13:00", vuelo.getHoraLlegada());
    }

    @Test
    public void testAddVueloAvionNoExiste() {
        try {

            pm.addVuelo("V4", "A99", "Sevilla", "Lisboa", "19:00", "21:00");
            Assert.fail("Debería haber lanzado excepción: Avión no existe");
        } catch (AvionNotFoundException e) {
            Assert.assertEquals("El avion no existe", e.getMessage());
        }
    }

    @Test
    public void testFacturarMaleta() {

        Maleta maleta = pm.facturarMaleta("Paco", "V1");
        Assert.assertNotNull(maleta);
        Assert.assertEquals("Paco", maleta.getUsuari());

    }

    @Test
    public void testFacturarMaletaVueloNoExiste() {
        try {
            pm.facturarMaleta("U2", "V99");
            Assert.fail("Debería haber lanzado excepción: Vuelo no existe");
        } catch (VueloNotFoundException e) {
            Assert.assertEquals("El vuelo no existe", e.getMessage());
        }
    }

    @Test
    public void testObtenerMaletasVuelo() {

        pm.facturarMaleta("U1", "V1");
        pm.facturarMaleta("U2", "V1");

        Queue<Maleta> maletas = pm.getMaletasVuelo("V1");
        Assert.assertEquals(2, maletas.size());
        Maleta[] arrayMaletas = maletas.toArray(new Maleta[0]);
        Assert.assertEquals("U1", arrayMaletas[0].getUsuari());
        Assert.assertEquals("U2", arrayMaletas[1].getUsuari());
    }

    @Test
    public void testObtenerMaletasVueloNoExiste() {
        try {
            pm.getMaletasVuelo("V99");
            Assert.fail("Debería haber lanzado un error porque el vuelo no existe");
        } catch (VueloNotFoundException e) {
            Assert.assertEquals("El vuelo no existe", e.getMessage());
        }
    }
}
