package edu.upc.dsa;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.User;


import java.util.List;

public interface ProductManager {
    public Avion deliverOrder1(String name);
    public void addAvion(String id, String name, double price);

    public List<Maleta> getProductsByPrice();

    public void addOrder(Avion avion);

    public int numOrders();


    public Avion deliverOrder();

    Maleta getProduct(String c1);

    User getUser(String number);
    int numUsers();

    void deleteProduct(String id);

    List<Maleta> findAll();

    Maleta updateProduct(Maleta maleta);
    public void addUser(User user);
}

