package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.exception.CategoryNotFoundException;
import kg.attractor.jobsearch_remake.model.Category;
import kg.attractor.jobsearch_remake.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        log.info("Получение всех категорий");
        return categoryRepository.findAll();
    }

    public Category getById(Integer id) {
        log.info("Получение категории по id: {}", id);
        return categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Категория не найдена с id: {}", id);
                    return new CategoryNotFoundException();
                });
    }
}