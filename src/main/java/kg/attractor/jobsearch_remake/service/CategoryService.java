package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(Integer id);
}