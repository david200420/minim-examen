package models;

import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String nom;
    private List <Order> ordenes;

    public User(int id, String nom)  {
        this.id =id;
        this.nom =nom;
        ordenes = new ArrayList<Order>();

    }
    public String getNom() {
        return this.nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

}
