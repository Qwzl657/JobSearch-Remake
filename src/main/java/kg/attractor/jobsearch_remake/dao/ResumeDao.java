package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResumeDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Resume> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM resumes",
                new BeanPropertyRowMapper<>(Resume.class)
        );
    }

    public List<Resume> findByCategory(Integer categoryId) {
        return jdbcTemplate.query(
                "SELECT * FROM resumes WHERE category_id = ?",
                new BeanPropertyRowMapper<>(Resume.class), categoryId
        );
    }


    public Optional<Resume> findById(Integer id) {
        try {
            Resume resume = jdbcTemplate.queryForObject(
                    "SELECT * FROM resumes WHERE id = ?",
                    new BeanPropertyRowMapper<>(Resume.class), id
            );
            return Optional.ofNullable(resume);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    public List<Resume> findByApplicantId(Integer applicantId) {
        return jdbcTemplate.query(
                "SELECT * FROM resumes WHERE applicant_id = ?",
                new BeanPropertyRowMapper<>(Resume.class), applicantId
        );
    }

    public void create(Resume r) {
        String sql = """
                INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                r.getApplicantId(),
                r.getName(),
                r.getCategoryId(),
                r.getSalary(),
                r.isActive(),
                LocalDate.now(),
                LocalDate.now()
        );
    }

    public void update(Resume r) {
        String sql = """
                UPDATE resumes
                SET name=?, category_id=?, salary=?, is_active=?, update_time=?
                WHERE id=?
                """;
        jdbcTemplate.update(sql,
                r.getName(),
                r.getCategoryId(),
                r.getSalary(),
                r.isActive(),
                LocalDate.now(),
                r.getId()
        );
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM resumes WHERE id = ?", id);
    }

    public Integer createAndReturnId(Resume r) {
        String sql = """
            INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
        jdbcTemplate.update(sql,
                r.getApplicantId(),
                r.getName(),
                r.getCategoryId(),
                r.getSalary(),
                r.isActive(),
                LocalDate.now(),
                LocalDate.now()
        );
        return jdbcTemplate.queryForObject(
                "SELECT MAX(id) FROM resumes",
                Integer.class
        );
    }

}