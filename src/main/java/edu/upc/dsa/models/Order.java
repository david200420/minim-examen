package models;
import java.util.*;

public class Order {

    private int id;
    private User user;
    private Date date;
    private List <Product> pedido;

    public Order(String dni) {
        this.user = new User(0,dni); // pasa de string a int
        this.pedido = new ArrayList<>(); //Importante inicializar la lista pq si no se tendra que crear en el test y no lo hace
    }
    public int getId() {
        return id;
    }

    public void addLP(int i, String s) {
        Product p = new Product(i,s);
        this.pedido.add(p);
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
