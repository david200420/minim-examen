package edu.upc.dsa.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private User user;
    private List <models.Product> pedido;

    public Order(String dni) {
        this.user = new User(0,dni); // pasa de string a int
        this.pedido = new ArrayList<>(); //Importante inicializar la lista pq si no se tendra que crear en el test y no lo hace
    }
    public Order(int id, User user, List <models.Product> pedido) {
        this.id = id;
        this.user = user;
        this.pedido = pedido;
    }
    public int getId() {
        return id;
    }

    public void addLP(int i, String s) {
        models.Product p = new models.Product(i,s);
        this.pedido.add(p);
    }
    public List <models.Product> getPedidos() {
        return pedido;
    }
    public String getLP(int i){
        return pedido.get(i).getNom();
    }
    public String getUser() {
        return user.getNom();
    }
    public User getUser1() {
        return this.user;
    }
}
