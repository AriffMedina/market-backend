package mx.edu.tecdesoftware.market_backend_2026_3_a.domain.service;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Product;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.ProductRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.ProductoCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    public List<Product> getAll(){
        return productRepository.getAll();
    }

    public Optional<Product> getProduct(int productId){
        return productRepository.getProduct(productId);
    }

    public Optional<List<Product>> getByCategory(int categoryId){
        return productRepository.getByCategory(categoryId);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public boolean delete(int productId){
        // Verificar que existe antes de borrarlo
        if (getProduct(productId).isPresent()){
            productRepository.delete(productId);
            return true;
        }

        return false;
    }
}
