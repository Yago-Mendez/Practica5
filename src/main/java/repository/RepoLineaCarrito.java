package repository;

import entity.LineaCarrito;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface RepoLineaCarrito extends CrudRepository<LineaCarrito, Long>  {
    
    List<LineaCarrito> findByCarritoId(Long idCarrito);

}
