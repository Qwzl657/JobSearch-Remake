package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResponseDao {

    private final JdbcTemplate jdbcTemplate;

    public void respond(Long userId, Long vacancyId) {
        String sql = "INSERT INTO responses (user_id, vacancy_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, userId, vacancyId);
    }

    public List<Vacancy> findUserVacancies(Long userId) {
        String sql = """
                SELECT v.* FROM vacancies v
                JOIN responses r ON v.id = r.vacancy_id
                WHERE r.user_id = ?
                """;

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Vacancy.class), userId);
    }

    public List<User> findUsersByVacancyId(Long vacancyId) {
        String sql = """
                SELECT u.* FROM users u
                JOIN responses r ON u.id = r.user_id
                WHERE r.vacancy_id = ?
                """;

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), vacancyId);
    }
}