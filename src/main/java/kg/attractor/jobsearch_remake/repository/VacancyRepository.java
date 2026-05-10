package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.Vacancy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findByActiveTrue();
    Page<Vacancy> findByActiveTrue(Pageable pageable);
    List<Vacancy> findByCategoryId(Integer categoryId);
    List<Vacancy> findByAuthorId(Long authorId);
    Page<Vacancy> findByAuthorId(Long authorId, Pageable pageable);

    @Query(value = """
    SELECT v.* FROM vacancies v
    LEFT JOIN responded_applicants r ON r.vacancy_id = v.id
    WHERE v.is_active = true
    GROUP BY v.id
    ORDER BY COUNT(r.id) DESC
    """,
            countQuery = "SELECT COUNT(DISTINCT v.id) FROM vacancies v WHERE v.is_active = true",
            nativeQuery = true)
    Page<Vacancy> findActiveOrderByResponseCount(Pageable pageable);
}