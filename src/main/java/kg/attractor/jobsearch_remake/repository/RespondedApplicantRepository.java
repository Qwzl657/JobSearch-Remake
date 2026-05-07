package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Long> {
    List<RespondedApplicant> findByVacancyId(Long vacancyId);
    List<RespondedApplicant> findByResumeId(Long resumeId);
}