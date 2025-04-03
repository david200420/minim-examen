package edu.upc.dsa;
import java.util.List;
import edu.upc.dsa.exceptions.AvionNotFoundException;
import edu.upc.dsa.exceptions.VueloNotFoundException;
import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.*;


public class AvionesManagerImpl implements AvionesManager {
    private Map<String, Avion> aviones;
    private Map<String, Vuelo> vuelos;

    private static final Logger logger = LogManager.getLogger(AvionesManagerImpl.class);
    private static AvionesManagerImpl instance;

    public static AvionesManagerImpl getInstance() { //Se crea una instancia para poder tener una comun entre los diferentes clientes
        if (instance == null) {
            instance = new AvionesManagerImpl();
        }
        return instance;
    }

    public AvionesManagerImpl() {
        aviones = new HashMap<>();
        vuelos = new HashMap<>();

    }

    @Override
    public void addAvion(String id, String name, String Compania) {
        logger.info("Adding Avion with id " + id + " and name " + name);
        aviones.put(id,new Avion(id,name,Compania));
    }
    @Override
    public int numVols() {
        return vuelos.size();
    }
    @Override
    public void addVuelo(String id, String avion, String origen, String destino, String horaSalida, String horaLlegada){
        logger.info("Adding Vuelo");
        if (!aviones.containsKey(avion)) {
            logger.error("No existe el avión con id " + avion);
            throw new AvionNotFoundException("El avion no existe");
        }
        Vuelo vuelo = new Vuelo(id, aviones.get(avion), origen, destino, horaSalida, horaLlegada);
        vuelos.put(id, vuelo);
        logger.info("Vuelo " + id + " agregado/modificado correctamente");
    }
    @Override
    public Avion getAvion(String id){
        return aviones.get(id);
    }

    @Override
    public Vuelo getVuelo(String id){
        return vuelos.get(id);
    }

    @Override
    public Maleta facturarMaleta(String usuario, String vueloId) {
        if (!vuelos.containsKey(vueloId)) {
            logger.error("Vuelo " + vueloId + " no existe");
            throw new VueloNotFoundException("El vuelo no existe");
        }
        Vuelo v = vuelos.get(vueloId);
        Maleta maleta = new Maleta(vueloId, usuario);
        v.getMaletas().add(maleta);
        logger.info("Maleta facturada para el usuario " + usuario + " en el vuelo " + vueloId);
        return maleta;
    }

    @Override
    public Queue<Maleta> getMaletasVuelo(String vueloId) {
        if (!vuelos.containsKey(vueloId)) {
            logger.error("Vuelo " + vueloId + " no existe");
            throw new VueloNotFoundException("El vuelo no existe");
        }
        Vuelo v = vuelos.get(vueloId);
        return v.getMaletas();
    }

}
