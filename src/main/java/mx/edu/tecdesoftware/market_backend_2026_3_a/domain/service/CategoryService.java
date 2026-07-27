package mx.edu.tecdesoftware.market_backend_2026_3_a.domain.service;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Category;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int categoryId){
        return categoryRepository.getCategory(categoryId);
    }

    public Category save(Category category){
        return categoryRepository.save(category);
    }

    public boolean delete(int categoryId){
        // Verificar que existe antes de borrarla
        if (getCategory(categoryId).isPresent()){
            categoryRepository.delete(categoryId);
            return true;
        }

        return false;
    }
}
