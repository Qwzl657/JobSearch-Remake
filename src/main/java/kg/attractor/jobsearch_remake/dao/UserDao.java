package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), email);

        return users.stream().findFirst();
    }

    public List<User> findByName(String name) {
        String sql = "SELECT * FROM users WHERE name = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), name);
    }

    public List<User> findByPhone(String phone) {
        String sql = "SELECT * FROM users WHERE phone = ?";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), phone);
    }

    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}