package entity;

import jakarta.persistence.*;

@Entity
public class Carrito {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;

    @Column(nullable = false) public Long idUsuario;
    @Column(nullable = false) public String correoUsuario;
    @Column(nullable = false) public Double totalPrecio;

    public Carrito() {}

    public Carrito(Long idUsuario, String correoUsuario) {
        this.idUsuario = idUsuario;
        this.correoUsuario = correoUsuario;
        this.totalPrecio = 0.0;
    }

    public void actualizarTotalPrecio(Double nuevoTotalPrecio) {
        this.totalPrecio = nuevoTotalPrecio;
    }

    public void actualizarCorreoUsuario(String nuevoCorreoUsuario) {
        this.correoUsuario = nuevoCorreoUsuario;
    }

    public void actualizarIdUsuario(Long nuevoIdUsuario) {
        this.idUsuario = nuevoIdUsuario;
    }

    public Long getId() {
        return this.id;
    }

    public String getCorreoUsuario() {
        return this.correoUsuario;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public Double getTotalPrecio() {
        return this.totalPrecio;
    }


}
