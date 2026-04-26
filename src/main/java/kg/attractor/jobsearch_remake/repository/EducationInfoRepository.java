package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationInfoRepository extends JpaRepository<EducationInfo, Integer> {
    List<EducationInfo> findByResumeId(Integer resumeId);
    void deleteByResumeId(Integer resumeId);
}