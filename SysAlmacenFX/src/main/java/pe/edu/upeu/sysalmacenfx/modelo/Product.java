package pe.edu.upeu.sysalmacenfx.modelo;


import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double price;
    private int stock;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State { ACTIVE, DISACTIVE }

    // Constructor vacío (necesario para JPA)
    public Product() {
    }

    // Constructor con todos los campos
    public Product(String name, double price, int stock, State state) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.state = state;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
