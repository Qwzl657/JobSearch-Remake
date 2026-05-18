package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.model.RespondedApplicant;
import kg.attractor.jobsearch_remake.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    List<RespondedApplicant> findByResume(Resume resume);
    List<RespondedApplicant> findByVacancy(Vacancy vacancy);
    boolean existsByResumeAndVacancy(Resume resume, Vacancy vacancy);
    void deleteByResume(Resume resume);
}