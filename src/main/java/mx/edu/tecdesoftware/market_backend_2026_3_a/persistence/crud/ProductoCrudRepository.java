package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud;

import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoCrudRepository extends JpaRepository<Producto, Integer> {

    // Query method
    /*
        SELECT *
        FROM Categorias
        WHERE id_categoria = ?
        ORDER BY nombre ASC
     */
    //Obtener una lista de productos filtrados por id de categoría
    // y ordenados ascendentemente por nombre
    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    //Obtener los productos escasos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad, boolean estado);
}
