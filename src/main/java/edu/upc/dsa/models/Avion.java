package edu.upc.dsa.models;

public class Avion {

    private String id;
    private String modelo;
    private String compania;


    public Avion(String id, String modelo, String compania) {
        this.id = id;
        this.modelo = modelo;
        this.compania = compania;
    }
    public Avion() {
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getModelo() {
        return modelo;
    }


    public String getCompania() {
        return this.compania;
    }



}

