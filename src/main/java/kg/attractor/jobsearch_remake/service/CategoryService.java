package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.model.Category;
import kg.attractor.jobsearch_remake.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        log.info("Fetching all categories");
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {
        log.info("Fetching category by id: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Category not found with id: {}", id);
                    return new NoSuchElementException("Category not found: " + id);
                });
    }

    public List<Category> getByParentId(Integer parentId) {
        log.info("Fetching categories by parent id: {}", parentId);
        return categoryRepository.findByParentId(parentId);
    }
}