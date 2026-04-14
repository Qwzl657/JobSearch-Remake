package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.EducationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EducationInfoDao {

    private final JdbcTemplate jdbcTemplate;

    public List<EducationInfo> findByResumeId(Integer resumeId) {
        return jdbcTemplate.query(
                "SELECT * FROM education_info WHERE resume_id = ?",
                new BeanPropertyRowMapper<>(EducationInfo.class), resumeId
        );
    }

    public Optional<EducationInfo> findById(Integer id) {
        try {
            EducationInfo info = jdbcTemplate.queryForObject(
                    "SELECT * FROM education_info WHERE id = ?",
                    new BeanPropertyRowMapper<>(EducationInfo.class), id
            );
            return Optional.ofNullable(info);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void create(EducationInfo info) {
        String sql = """
                INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                info.getResumeId(),
                info.getInstitution(),
                info.getProgram(),
                info.getStartDate(),
                info.getEndDate(),
                info.getDegree()
        );
    }

    public void update(EducationInfo info) {
        String sql = """
                UPDATE education_info
                SET institution=?, program=?, start_date=?, end_date=?, degree=?
                WHERE id=?
                """;
        jdbcTemplate.update(sql,
                info.getInstitution(),
                info.getProgram(),
                info.getStartDate(),
                info.getEndDate(),
                info.getDegree(),
                info.getId()
        );
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM education_info WHERE id = ?", id);
    }

    public void deleteByResumeId(Integer resumeId) {
        jdbcTemplate.update("DELETE FROM education_info WHERE resume_id = ?", resumeId);
    }
}
