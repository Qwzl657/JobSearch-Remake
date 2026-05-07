package kg.attractor.jobsearch_remake.repository;

import kg.attractor.jobsearch_remake.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByAccountType(String accountType);

    Optional<User> findByResetPasswordToken(String token);
}