package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Purchase;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.PurchaseRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.CompraCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Compra;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.CompraProducto;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.PurchaseItemMapper;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // le dices a spring que esta clase se comunicará con la db
public class CompraRepository implements PurchaseRepository {

    @Autowired
    private CompraCrudRepository compraCrudRepository;
    @Autowired
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private PurchaseMapper purchaseMapper;
    @Autowired
    private PurchaseItemMapper purchaseItemMapper;

    // SELECT * FROM compras
    public List<Purchase> getAll(){
        List<Compra> compras = (List<Compra>) compraCrudRepository.findAll();
        return purchaseMapper.toPurchases(compras);
    }

    /*
    SELECT *
    FROM compras
    WHERE id_cliente = ?
     */
    // Obtener el listado de compras filtrado por id de cliente
    public Optional<List<Purchase>> getByClient(String clientId){
        List<Compra> compras = compraCrudRepository.findByIdCliente(clientId);
        return Optional.of(purchaseMapper.toPurchases(compras));
    }

    // Guardar una compra junto con sus productos (cascada)
    public Purchase save(Purchase purchase){
        Compra compra = purchaseMapper.toCompra(purchase);

        // Paso crítico de integridad referencial: cada producto de la compra
        // debe referenciar a la compra principal antes de delegar el guardado,
        // ya que id_compra se resuelve mediante @MapsId a partir de esta relación.
        if (purchase.getItems() != null){
            List<CompraProducto> compraProductos = purchaseItemMapper.toCompraProductos(purchase.getItems());
            for (int i = 0; i < compraProductos.size(); i++) {
                CompraProducto compraProducto = compraProductos.get(i);
                compraProducto.setCompra(compra);
                // getReferenceById NO hace fetch a la BD, solo crea un proxy
                // con el id, suficiente para que @MapsId("idProducto") resuelva la PK
                Integer productoId = purchase.getItems().get(i).getProductId();
                compraProducto.setProducto(productoCrudRepository.getReferenceById(productoId));
            }
            compra.setCompraProductos(compraProductos);
        }

        Compra compraGuardada = compraCrudRepository.save(compra);
        return purchaseMapper.toPurchase(compraGuardada);
    }
}