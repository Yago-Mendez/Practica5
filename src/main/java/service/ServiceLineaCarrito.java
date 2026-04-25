package service;

import entity.Carrito;
import entity.LineaCarrito;
import repository.RepoCarrito;
import repository.RepoLineaCarrito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ServiceLineaCarrito {
    @Autowired
    private RepoLineaCarrito repoLinea;
    @Autowired
    private RepoCarrito repoCarrito;

    @Transactional 
    public LineaCarrito añadirLinea(Long idCarrito, LineaCarrito nuevaLinea) {
        Carrito carrito = repoCarrito.findById(idCarrito)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrito no encontrado"));
        
        
        nuevaLinea.carrito = carrito;
        nuevaLinea.actualizarCosteLinea();

        carrito.actualizarTotalPrecio(carrito.getTotalPrecio() + nuevaLinea.getCosteLinea());
        
        repoCarrito.save(carrito); 
        return repoLinea.save(nuevaLinea); 
    }

    @Transactional
    public void borrarLinea(Long idLinea) {
        LineaCarrito linea = repoLinea.findById(idLinea)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Línea no encontrada"));
        
        Carrito carrito = linea.carrito;
        
        carrito.actualizarTotalPrecio(carrito.getTotalPrecio() - linea.getCosteLinea());
        
        repoCarrito.save(carrito);
        repoLinea.delete(linea);
    }
}