package pe.edu.upeu.sysalmacenfx.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "upeu_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre", nullable = false)
    @Size(max = 30)
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @Size(max = 30)
    private String apellido;

    @Size(max = 8)
    @Column(name = "dni", nullable = false)
    private String dni;

    @Column(name = "user", nullable = false, unique = true, length = 20)
    private String user;

    @Size(max = 9)
    @Column(name = "telefono", nullable = false)
    private String telf;

    @Column(name = "clave", nullable = false, length = 100)
    private String clave;

    @JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil")
    @ManyToOne(optional = false)
    private Perfil perfil;

    // Nuevos campos para la venta
    @Transient // Este campo no será persistido en la base de datos
    private int cantidad;

    @Transient // Este campo no será persistido en la base de datos
    private double precio;

    @Transient // Este campo no será persistido en la base de datos
    private double total;

    // Método para calcular el total de la venta
    public double getTotal() {
        return this.cantidad * this.precio;
    }

    // Métodos adicionales para setear y obtener la cantidad y precio
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.total = getTotal(); // Recalcular el total cada vez que cambie la cantidad
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        this.total = getTotal(); // Recalcular el total cada vez que cambie el precio
    }
}

