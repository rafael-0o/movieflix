package br.com.movieflix.Service;

import br.com.movieflix.Entity.Category;
import br.com.movieflix.Repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }
    @Transactional
    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }
    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }
    public void delete(Long id){
        categoryRepository.deleteById(id);
    }
}
