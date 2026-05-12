package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.ContactInfo;
import kg.attractor.jobsearch_remake.model.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
    List<ContactInfo> findByResume(Resume resume);
    void deleteByResume(Resume resume);
}