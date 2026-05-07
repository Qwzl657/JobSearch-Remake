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
    List<Vacancy> findByIsActiveTrue();
    Page<Vacancy> findByIsActiveTrue(Pageable pageable);
    List<Vacancy> findByCategoryId(Integer categoryId);
    List<Vacancy> findByAuthorId(Long authorId);
    Page<Vacancy> findByAuthorId(Long authorId, Pageable pageable);

    @Query("""
            SELECT v FROM Vacancy v
            LEFT JOIN RespondedApplicant r ON r.vacancyId = v.id
            WHERE v.isActive = true
            GROUP BY v.id
            ORDER BY COUNT(r.id) DESC
            """)
    Page<Vacancy> findActiveOrderByResponseCount(Pageable pageable);
}