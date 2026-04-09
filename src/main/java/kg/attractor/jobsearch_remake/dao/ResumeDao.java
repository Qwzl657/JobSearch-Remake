package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@RequiredArgsConstructor
public class ResumeDao {

    private final JdbcTemplate jdbcTemplate;

    public List<Resume> findByCategory(Integer categoryId) {
        String sql = "SELECT * FROM resumes WHERE category_id = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Resume.class), categoryId);
    }

    public void create(Resume r) {
        String sql = """
        INSERT INTO resumes (applicant_id, name, category_id, salary, is_active)
        VALUES (?, ?, ?, ?, ?)
    """;

        jdbcTemplate.update(sql,
                r.getApplicantId(),
                r.getName(),
                r.getCategoryId(),
                r.getSalary(),
                r.isActive());
    }

    public void update(Resume r) {
        String sql = """
        UPDATE resumes
        SET name=?, category_id=?, salary=?, is_active=?
        WHERE id=?
    """;

        jdbcTemplate.update(sql,
                r.getName(),
                r.getCategoryId(),
                r.getSalary(),
                r.isActive(),
                r.getId());
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM resumes WHERE id = ?", id);
    }
}