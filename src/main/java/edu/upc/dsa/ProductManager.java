package edu.upc.dsa;

import edu.upc.dsa.exceptions.TrackNotFoundException;
import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.User;


import java.util.List;



import java.util.List;

public interface ProductManager {
    public Order deliverOrder1(String name);
    public void addProduct(String id, String name, double price);

    public List<models.Product> getProductsByPrice();

    public void addOrder(Order order);

    public int numOrders();


    public Order deliverOrder();

    models.Product getProduct(String c1);

    User getUser(String number);
    int numUsers();

    void deleteProduct(String id);

    List<models.Product> findAll();

    models.Product updateProduct(models.Product product);
    public void addUser(User user);
}

