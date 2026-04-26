package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Integer> {
    List<Vacancy> findByIsActiveTrue();
    Page<Vacancy> findByIsActiveTrue(Pageable pageable);
    List<Vacancy> findByCategoryId(Integer categoryId);
    List<Vacancy> findByAuthorId(Integer authorId);
    Page<Vacancy> findByAuthorId(Integer authorId, Pageable pageable);
}