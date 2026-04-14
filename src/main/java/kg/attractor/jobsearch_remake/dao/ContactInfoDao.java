package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ContactInfoDao {

    private final JdbcTemplate jdbcTemplate;

    public List<ContactInfo> findByResumeId(Integer resumeId) {
        return jdbcTemplate.query(
                "SELECT * FROM contact_info WHERE resume_id = ?",
                new BeanPropertyRowMapper<>(ContactInfo.class), resumeId
        );
    }

    public Optional<ContactInfo> findById(Integer id) {
        try {
            ContactInfo info = jdbcTemplate.queryForObject(
                    "SELECT * FROM contact_info WHERE id = ?",
                    new BeanPropertyRowMapper<>(ContactInfo.class), id
            );
            return Optional.ofNullable(info);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void create(ContactInfo info) {
        String sql = """
                INSERT INTO contact_info (resume_id, type_id, value)
                VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                info.getResumeId(),
                info.getTypeId(),
                info.getValue()
        );
    }

    public void update(ContactInfo info) {
        String sql = """
                UPDATE contact_info
                SET type_id=?, value=?
                WHERE id=?
                """;
        jdbcTemplate.update(sql,
                info.getTypeId(),
                info.getValue(),
                info.getId()
        );
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM contact_info WHERE id = ?", id);
    }

    public void deleteByResumeId(Integer resumeId) {
        jdbcTemplate.update("DELETE FROM contact_info WHERE resume_id = ?", resumeId);
    }
}
