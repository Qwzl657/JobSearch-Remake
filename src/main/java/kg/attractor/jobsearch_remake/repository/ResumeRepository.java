package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    List<Resume> findByApplicant(User applicant);
    Page<Resume> findByApplicant(User applicant, Pageable pageable);
    List<Resume> findByCategory_Id(Integer categoryId);
}