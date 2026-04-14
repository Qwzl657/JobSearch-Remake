package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.WorkExperienceInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WorkExperienceInfoDao {

    private final JdbcTemplate jdbcTemplate;

    public List<WorkExperienceInfo> findByResumeId(Integer resumeId) {
        return jdbcTemplate.query(
                "SELECT * FROM work_experience_info WHERE resume_id = ?",
                new BeanPropertyRowMapper<>(WorkExperienceInfo.class), resumeId
        );
    }

    public Optional<WorkExperienceInfo> findById(Integer id) {
        try {
            WorkExperienceInfo info = jdbcTemplate.queryForObject(
                    "SELECT * FROM work_experience_info WHERE id = ?",
                    new BeanPropertyRowMapper<>(WorkExperienceInfo.class), id
            );
            return Optional.ofNullable(info);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void create(WorkExperienceInfo info) {
        String sql = """
                INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
                VALUES (?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                info.getResumeId(),
                info.getYears(),
                info.getCompanyName(),
                info.getPosition(),
                info.getResponsibilities()
        );
    }

    public void update(WorkExperienceInfo info) {
        String sql = """
                UPDATE work_experience_info
                SET years=?, company_name=?, position=?, responsibilities=?
                WHERE id=?
                """;
        jdbcTemplate.update(sql,
                info.getYears(),
                info.getCompanyName(),
                info.getPosition(),
                info.getResponsibilities(),
                info.getId()
        );
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM work_experience_info WHERE id = ?", id);
    }

    public void deleteByResumeId(Integer resumeId) {
        jdbcTemplate.update("DELETE FROM work_experience_info WHERE resume_id = ?", resumeId);
    }
}