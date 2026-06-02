package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {
    private ProductoCrudRepository productoCrudRepository;

    //SELECT * FROM productos
    public List<Producto> getAll(){
        // Se "castea" de Iterable a la Lista
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    /*
    SELECT * FROM producto
    WHERE cantidad_stock < ?
    AND estado = true;
     */

    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado( cantidad, true);
    }

    //Obtener un producto dado el id
    public Optional<Producto> getProductoById(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    //Guardar un producto
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    /*
    INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
    VALUES (?, ?, ?, ?);
     */

    //Eliminar por id
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
