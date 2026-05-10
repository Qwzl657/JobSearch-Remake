package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Long> {
    List<WorkExperienceInfo> findByResumeId(Long resumeId);
    void deleteByResumeId(Long resumeId);
}