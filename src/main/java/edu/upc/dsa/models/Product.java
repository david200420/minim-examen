package models;

public class Product {

    private double preu;
    private String nom;
    private String id;
    private int cantitat;

    public Product(int cantitat, String name) {
        this.cantitat= cantitat;
        this.nom = name;//y si lo son pues le pone un valor, asi ahorramos constructores.
    }

    public Product(String id, String name, double price  ) {
        this.id= id;
        this.preu = price;
        this.nom = name;//y si lo son pues le pone un valor, asi ahorramos constructores.
    }
    public String getNom() {
        return this.nom;
    }
    public String getId () {
        return this.id;
    }
    public double getPreu() {
        return preu;
    }

    public double sales() {
        return this.preu;
    }
}
