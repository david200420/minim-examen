package edu.upc.dsa;


import java.util.LinkedList;
import java.util.List;

import edu.upc.dsa.models.Avion;
import edu.upc.dsa.models.Maleta;
import edu.upc.dsa.models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.util.*;
import java.util.Comparator;

public class ProductManagerImpl implements ProductManager {
    private List<Maleta> maletaList;
    private Queue<Avion> avionQueue;
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
        maletaList = new ArrayList<>();
        avionQueue = new LinkedList<>();
        users = new HashMap<>();
    }

    @Override
    public void addAvion(String id, String name, double price) {
        logger.info("Adding product with id " + id + " and name " + name);
        maletaList.add(new Maleta(id, name, price));

    }

    @Override
    public List<Maleta> getProductsByPrice() {
        logger.info("Getting products by price");
        maletaList.sort(Comparator.comparing(Maleta::getPreu).reversed());
        return maletaList;
    }

    @Override
    public void addOrder(Avion avion) {
        avionQueue.add(avion);
        users.put(avion.getUser(), avion.getUser1());
    }

    @Override
    public int numOrders() {
        return avionQueue.size();
    }

    @Override
    public Avion deliverOrder() {
        Avion avion = avionQueue.poll();
        return avion;
    }

    public Avion deliverOrder1(String name) {
        for (Avion avion : avionQueue) {
            if (avion.getUser().equals(name)) {
                return avion; // Devuelve la orden si el usuario coincide
            }
        }
        return null; // Si no encuentra ninguna orden con ese usuario
    }

    @Override
    public Maleta getProduct(String c1) {
        for (Maleta p : this.maletaList) {
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
        for (Maleta p : this.maletaList) {
            if (p.getId().equals(id)) {
                maletaList.remove(p);
            }
        }

    }


    @Override
    public List<Maleta> findAll() {
        return maletaList;
    }

    @Override
    public Maleta updateProduct(Maleta maleta) {
        for (Maleta p : this.maletaList) {
            if (p.getId().equals(maleta.getId())) {
                return maleta;
            }
        }
        return null;
    }

    public List<Maleta> getProductList() {
        return maletaList;
    }

    @Override
    public void addUser(User user) {
        users.put(user.getNom(),user);
    }
}
