package service;

import entity.Carrito;
import repository.RepoCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ServiceCarrito {
    @Autowired
    private RepoCarrito repoCarrito;

    public Carrito crear(Carrito carrito) {
        return repoCarrito.save(carrito);
    }

    public Carrito buscarPorId(Long id) {
        return repoCarrito.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado"));
    }

    public void eliminar(Long id) {
        if (!repoCarrito.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado");
        }
        repoCarrito.deleteById(id);
    }

    public Carrito actualizar(Long id, Carrito nuevo) {
        Carrito c = buscarPorId(id); 
        c.actualizarIdUsuario(nuevo.getIdUsuario());
        c.actualizarCorreoUsuario(nuevo.getCorreoUsuario());
        return repoCarrito.save(c); 
    }
}