package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.model.WorkExperienceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkExperienceInfoRepository extends JpaRepository<WorkExperienceInfo, Long> {
    List<WorkExperienceInfo> findByResume(Resume resume);
    void deleteByResume(Resume resume);
}