package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.exception.CategoryNotFoundException;
import kg.attractor.jobsearch_remake.model.Category;
import kg.attractor.jobsearch_remake.repository.CategoryRepository;
import kg.attractor.jobsearch_remake.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Категория не найдена с id: {}", id);
                    return new CategoryNotFoundException();
                });
    }
}