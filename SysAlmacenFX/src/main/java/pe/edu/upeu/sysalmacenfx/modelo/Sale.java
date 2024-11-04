package pe.edu.upeu.sysalmacenfx.modelo;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int idCustomer;
    private int idSeller;
    private String numberSales;
    private LocalDate saleDate;
    private double amount;

    @Enumerated(EnumType.STRING)
    private State state;

    public enum State { ACTIVE, DISACTIVE }

    // Getters y setters
}
