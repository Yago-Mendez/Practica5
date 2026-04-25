package edu.comillas.icai.gitt.pat.spring.Practica3;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import entity.Carrito;
import entity.LineaCarrito;
import service.ServiceCarrito;
import service.ServiceLineaCarrito;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/carrito")
public class ControladorCarrito {
    @Autowired private ServiceCarrito serviceCarrito;
    @Autowired private ServiceLineaCarrito serviceLinea;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Carrito crea(@RequestBody Carrito c) { 
        return serviceCarrito.crear(c); 
    }

    @GetMapping("/{id}")
    public Carrito buscar(@PathVariable Long id) { 
        return serviceCarrito.buscarPorId(id); 
    }

    @PutMapping("/{id}")
    public Carrito actualizar(@PathVariable Long id, @RequestBody Carrito c) { 
        return serviceCarrito.actualizar(id, c); 
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id) { 
        serviceCarrito.eliminar(id); 
    }

    @PostMapping("/{idCarrito}/lineas")
    @ResponseStatus(HttpStatus.CREATED)
    public LineaCarrito añadirLinea(@PathVariable Long idCarrito, @RequestBody LineaCarrito linea) {
        return serviceLinea.añadirLinea(idCarrito, linea);
    }

    @DeleteMapping("/lineas/{idLinea}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrarLinea(@PathVariable Long idLinea) {
        serviceLinea.borrarLinea(idLinea);
    }
}