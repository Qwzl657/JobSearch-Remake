package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Integer> {
}