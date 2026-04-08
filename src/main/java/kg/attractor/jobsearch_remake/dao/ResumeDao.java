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

    public List<Resume> findAll() {
        return jdbcTemplate.query("SELECT * FROM resumes",
                new BeanPropertyRowMapper<>(Resume.class));
    }

    public List<Resume> findByCategory(String category) {
        String sql = "SELECT * FROM resumes WHERE category = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Resume.class), category);
    }

    public List<Resume> findByUserId(Long userId) {
        String sql = "SELECT * FROM resumes WHERE user_id = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Resume.class), userId);
    }
}