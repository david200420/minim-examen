package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.Vuelo;


import java.util.List;
import java.util.Queue;

public interface AvionesManager {
    public void addAvion(String id, String name, String Compañia);
    public int numVols();
    public void addVuelo(String id, String avion, String origen, String destino, String horaSalida, String horaLlegada);

    public Avion getAvion(String id);
    public Vuelo getVuelo(String id);

    public Maleta facturarMaleta(String nombre, String vuelo);
    public Queue<Maleta> getMaletasVuelo(String nombre);


}

