package mx.edu.tecdesoftware.market_backend_2026_3_a.persistence;

import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.Category;
import mx.edu.tecdesoftware.market_backend_2026_3_a.domain.repository.CategoryRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.crud.CategoriaCrudRepository;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.entity.Categoria;
import mx.edu.tecdesoftware.market_backend_2026_3_a.persistence.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoriaRepository implements CategoryRepository {

    @Autowired
    private CategoriaCrudRepository categoriaCrudRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    //SELECT * FROM categorias
    public List<Category> getAll(){
        List<Categoria> categorias = (List<Categoria>) categoriaCrudRepository.findAll();
        return categorias.stream().map(categoryMapper::toCategory).toList();
    }

    //Obtener una categoria dado el id
    public Optional<Category> getCategory(int categoryId){
        return categoriaCrudRepository.findById(categoryId)
                .map(categoryMapper::toCategory);
    }

    //Guardar una categoria
    public Category save(Category category){
        Categoria categoria = categoryMapper.toCategoria(category);
        return categoryMapper.toCategory(categoriaCrudRepository.save(categoria));
    }

    //Eliminar por id
    public void delete(int categoryId){
        categoriaCrudRepository.deleteById(categoryId);
    }
}
