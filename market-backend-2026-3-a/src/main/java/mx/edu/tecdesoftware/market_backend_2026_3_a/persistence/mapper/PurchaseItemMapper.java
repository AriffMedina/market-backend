package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.PurchaseItem;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idCompra", target = "purchaseId"),
            @Mapping(source = "id.idproducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "total", target = "price"),
            @Mapping(source = "estado", target = "status")
    })
    PurchaseItem toPurchaseItem(CompraProducto compraProducto);

    List<PurchaseItem> toPurchaseItems(List<CompraProducto> compraProductos);

    @InheritInverseConfiguration
    @Mapping(target = "compra", ignore = true)
    @Mapping(target = "producto", ignore = true)
    CompraProducto toCompraProducto(PurchaseItem purchaseItem);
}