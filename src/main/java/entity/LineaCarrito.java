package entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class LineaCarrito {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne (optional=false) @JoinColumn(name = "id_carrito", nullable = false) public @OnDelete(action = OnDeleteAction.CASCADE) Carrito carrito;
    @Column(nullable = false) public Long idArticulo;
    @Column(nullable = false) public Double precioUnitario;
    @Column(nullable = false) public Double numeroUnidades;

    @Column(nullable = false) public Double costeLinea;

    public LineaCarrito() {}

    public LineaCarrito(Carrito carrito, Long idArticulo, Double precioUnitario, Double numeroUnidades) {
        this.carrito = carrito;
        this.idArticulo = idArticulo;
        this.precioUnitario = precioUnitario;
        this.numeroUnidades = numeroUnidades;
        this.costeLinea = precioUnitario * numeroUnidades;
    }

    public void actualizarCosteLinea() {
        this.costeLinea = this.precioUnitario * this.numeroUnidades;
    }

    public void actualizarPrecioUnitario(Double nuevoPrecioUnitario) {
        this.precioUnitario = nuevoPrecioUnitario;
        actualizarCosteLinea();
    }

    public void actualizarNumeroUnidades(Double nuevoNumeroUnidades) {
        this.numeroUnidades = nuevoNumeroUnidades;
        actualizarCosteLinea();
    }

    public void actualizarIdArticulo(Long nuevoIdArticulo) {
        this.idArticulo = nuevoIdArticulo;
    }   

    public Long getId() {
        return this.id;
    }
    public Long getIdArticulo() {
        return this.idArticulo;
    }

    public Double getPrecioUnitario() {
        return this.precioUnitario;
    }
    public Double getNumeroUnidades() {
        return this.numeroUnidades;
    }
    public Double getCosteLinea() {
        return this.costeLinea;
    }
}

