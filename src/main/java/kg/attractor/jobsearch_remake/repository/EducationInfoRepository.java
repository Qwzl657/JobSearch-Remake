package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.EducationInfo;
import kg.attractor.jobsearch_remake.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationInfoRepository extends JpaRepository<EducationInfo, Long> {
    List<EducationInfo> findByResume(Resume resume);
    void deleteByResume(Resume resume);
}