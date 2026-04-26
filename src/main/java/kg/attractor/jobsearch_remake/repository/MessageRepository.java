package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findByRespondedApplicants(Integer respondedApplicantId);
}