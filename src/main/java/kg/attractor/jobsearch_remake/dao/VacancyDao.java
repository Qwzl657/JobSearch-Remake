package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VacancyDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> findAll() {
        return jdbcTemplate.query("SELECT * FROM vacancies",
                new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public List<Vacancy> findByCategory(String category) {
        String sql = "SELECT * FROM vacancies WHERE category = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Vacancy.class), category);
    }

    public List<Long> getRespondedVacancies(Long userId) {
        String sql = "SELECT vacancy_id FROM responses WHERE user_id = ?";
        return jdbcTemplate.queryForList(sql, Long.class, userId);
    }
}