package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class VacancyDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Vacancy> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM vacancies",
                new BeanPropertyRowMapper<>(Vacancy.class)
        );
    }

    public List<Vacancy> findByCategory(Integer categoryId) {
        String sql = "SELECT * FROM vacancies WHERE category_id = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Vacancy.class), categoryId);
    }

    public List<Vacancy> getActive() {
        String sql = "SELECT * FROM vacancies WHERE is_active = true";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Vacancy.class));
    }

    public void create(Vacancy v) {
        String sql = """
                INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                v.getName(),
                v.getDescription(),
                v.getCategoryId(),
                v.getSalary(),
                v.getExpFrom(),
                v.getExpTo(),
                v.isActive(),
                v.getAuthorId());
    }

    public void update(Vacancy v) {
        String sql = """
                UPDATE vacancies
                SET name=?, description=?, category_id=?, salary=?, exp_from=?, exp_to=?, is_active=?
                WHERE id=?
                """;

        jdbcTemplate.update(sql,
                v.getName(),
                v.getDescription(),
                v.getCategoryId(),
                v.getSalary(),
                v.getExpFrom(),
                v.getExpTo(),
                v.isActive(),
                v.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM vacancies WHERE id = ?", id);
    }
}