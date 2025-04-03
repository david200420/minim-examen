package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

import java.util.LinkedList;
import java.util.Queue;

public class Vuelo {
    private String id;
    private Avion avion;
    private String origen;
    private String destino;
    private String horaSalida;
    private String horaLlegada;
    private Queue<Maleta> maletas;

    public Vuelo(String id, Avion avion, String origen, String destino, String horaSalida, String horaLlegada) {
        this.id = id;
        this.avion = avion;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.maletas = new LinkedList<>();
    }

    public void facturarMaleta(Maleta maleta) {
        maletas.add(maleta);
    }

    public Queue<Maleta> getMaletas() {
        return maletas;
    }
}
