package edu.upc.dsa;

import edu.upc.dsa.exceptions.TrackNotFoundException;


import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import models.Product;

import java.util.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductManagerImpl implements ProductManager {
    private List<models.Product> productList;
    private Queue<Order> orderQueue;
    private HashMap<String, User> users;
    private static final Logger logger = LogManager.getLogger(ProductManagerImpl.class);
    private static ProductManagerImpl instance;

    public static ProductManagerImpl getInstance() { //Se crea una instancia para poder tener una comun entre los diferentes clientes
        if (instance == null) {
            instance = new ProductManagerImpl();
        }
        return instance;
    }

    public ProductManagerImpl() {
        productList = new ArrayList<>();
        orderQueue = new LinkedList<>();
        users = new HashMap<>();
    }

    @Override
    public void addProduct(String id, String name, double price) {
        logger.info("Adding product with id " + id + " and name " + name);
        productList.add(new models.Product(id, name, price));

    }

    @Override
    public List<models.Product> getProductsByPrice() {
        logger.info("Getting products by price");
        productList.sort(Comparator.comparing(models.Product::getPreu).reversed());
        return productList;
    }

    @Override
    public void addOrder(Order order) {
        orderQueue.add(order);
        users.put(order.getUser(),order.getUser1());
    }

    @Override
    public int numOrders() {
        return orderQueue.size();
    }

    @Override
    public Order deliverOrder() {
        Order order = orderQueue.poll();
        return order;
    }

    public Order deliverOrder1(String name) {
        for (Order order : orderQueue) {
            if (order.getUser().equals(name)) {
                return order; // Devuelve la orden si el usuario coincide
            }
        }
        return null; // Si no encuentra ninguna orden con ese usuario
    }

    @Override
    public models.Product getProduct(String c1) {
        for (models.Product p : this.productList) {
            if (p.getId().equals(c1)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public User getUser(String number) {
        return users.get(number);
    }
    @Override
    public int numUsers() {
        return users.size();
    }

    @Override
    public void deleteProduct(String id) {
        for (Product p : this.productList) {
            if (p.getId().equals(id)) {
                productList.remove(p);
            }
        }

    }


    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product updateProduct(Product product) {
        for (models.Product p : this.productList) {
            if (p.getId().equals(product.getId())) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void addUser(User user) {
        users.put(user.getNom(),user);
    }
}
