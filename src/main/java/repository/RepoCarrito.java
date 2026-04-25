package repository;

import entity.Carrito;
import org.springframework.data.repository.CrudRepository;

public interface RepoCarrito extends CrudRepository<Carrito, Long>  {
    
}
