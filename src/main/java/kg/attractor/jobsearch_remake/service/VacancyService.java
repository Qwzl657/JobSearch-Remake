package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.VacancyDao;
import kg.attractor.jobsearch_remake.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyDao vacancyDao;

    public List<Vacancy> getAll() {
        return vacancyDao.findAll();
    }

    public List<Vacancy> getByCategory(String category) {
        return vacancyDao.findByCategory(category);
    }
}