package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.List;

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

    public String getModel() {
        return this.modelo;
    }

    public void setId(int id) {
        this.id = String.valueOf(id);
    }

    public String getCompañia() {
        return this.compania;
    }



}

