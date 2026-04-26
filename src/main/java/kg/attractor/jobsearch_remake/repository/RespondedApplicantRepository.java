package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.RespondedApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondedApplicantRepository extends JpaRepository<RespondedApplicant, Integer> {
    List<RespondedApplicant> findByVacancyId(Integer vacancyId);
    List<RespondedApplicant> findByResumeId(Integer resumeId);
}