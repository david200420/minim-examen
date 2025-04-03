package edu.upc.dsa.models;

public class Maleta {

    private String usuari;
    private String id;


    public Maleta() {
    }

    public Maleta(String id, String name) {
        this.id= id;
        this.usuari = name;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getUsuari() {
        return usuari;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }



}
