package edu.upc.dsa;

import edu.upc.dsa.exceptions.TrackNotFoundException;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


import edu.upc.dsa.models.Product;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductManagerTest {
    ProductManager pm;

    @Before
    public void setUp() {
        pm = new ProductManagerImpl();
        pm.addProduct("C1", "Coca-cola zero", 2);
        pm.addProduct("C2", "Coca-cola", 2.5);
        pm.addProduct("B1", "Lomo queso", 3);
        pm.addProduct("B2", "bacon queso", 3.5);
    }

    @After
    public void tearDown() {
        this.pm = null;
    }

    @Test
    public void testProductByPrice() {
        List<Product> products = pm.getProductsByPrice();
        Assert.assertEquals(3.5, products.get(0).getPreu(),0);
        Assert.assertEquals(3, products.get(1).getPreu(), 0);
        Assert.assertEquals(2.5, products.get(2).getPreu(), 0);
        Assert.assertEquals(2, products.get(3).getPreu(), 0);
    }

    @Test
    public void testAddOrder() {
        Assert.assertEquals(0, pm.numOrders());
        Order o = new Order("381112838");
        o.addLP(2, "C1"); //, "coca-cola");
        o.addLP(1, "bocata de pernil");
        o.addLP(1, "donut");
        pm.addOrder(o);
        System.out.println(o.getLP(0));
        Assert.assertEquals(1, pm.numOrders());
    }

    @Test
    public void testDeliverOrder() {
        testAddOrder();
        Assert.assertEquals(1, pm.numOrders());
        Order o = pm.deliverOrder();
        Assert.assertEquals(0, pm.numOrders());
        Assert.assertEquals("381112838", o.getUser());
    }

    @Test
    public void testSales() {
        testDeliverOrder();
        Product p = pm.getProduct("C1");
        Assert.assertEquals(2, (int)p.sales());
    }

    @Test
    public void testOrdersByUser() {
        testSales();
        User u = pm.getUser("381112838");
        Order o = pm.deliverOrder1(u.getNom());
        List<Order> l = new ArrayList<Order>();
        l.add(o);
        Assert.assertEquals(1, l.size());
    }
}