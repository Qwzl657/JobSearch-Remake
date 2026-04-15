package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
        return jdbcTemplate.query(
                "SELECT * FROM vacancies WHERE category_id = ?",
                new BeanPropertyRowMapper<>(Vacancy.class), categoryId
        );
    }

    public List<Vacancy> getActive() {
        return jdbcTemplate.query(
                "SELECT * FROM vacancies WHERE is_active = true",
                new BeanPropertyRowMapper<>(Vacancy.class)
        );
    }


    public Optional<Vacancy> findById(Integer id) {
        try {
            Vacancy vacancy = jdbcTemplate.queryForObject(
                    "SELECT * FROM vacancies WHERE id = ?",
                    new BeanPropertyRowMapper<>(Vacancy.class), id
            );
            return Optional.ofNullable(vacancy);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    // НОВЫЙ: вакансии конкретного работодателя
    public List<Vacancy> findByAuthorId(Integer authorId) {
        return jdbcTemplate.query(
                "SELECT * FROM vacancies WHERE author_id = ?",
                new BeanPropertyRowMapper<>(Vacancy.class), authorId
        );
    }

    public void create(Vacancy v) {
        String sql = """
                INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                v.getName(),
                v.getDescription(),
                v.getCategoryId(),
                v.getSalary(),
                v.getExpFrom(),
                v.getExpTo(),
                v.isActive(),
                v.getAuthorId(),
                LocalDate.now(),
                LocalDate.now()
        );
    }

    public void update(Vacancy v) {
        String sql = """
                UPDATE vacancies
                SET name=?, description=?, category_id=?, salary=?, exp_from=?, exp_to=?, is_active=?, update_time=?
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
                LocalDate.now(),
                v.getId()
        );
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM vacancies WHERE id = ?", id);
    }
}