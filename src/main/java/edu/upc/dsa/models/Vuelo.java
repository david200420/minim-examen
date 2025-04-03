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

    public Vuelo() {

    }

    public Vuelo(String id) {
        this.id = id;
        maletas = new LinkedList<>();
    }

    public Vuelo(String id, Avion idavion, String origen, String destino, String horaSalida, String horaLlegada) {
        this.id = id;
        this.avion = idavion;
        this.origen = origen;
        this.destino = destino;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
        this.maletas = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Avion getAvion() {
        return avion;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public String getHoraLlegada() {
        return horaLlegada;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setHoraLlegada(String horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public void facturarMaleta(Maleta maleta) {
        maletas.add(maleta);
    }

    public Queue<Maleta> getMaletas() {
        return maletas;
    }
}
