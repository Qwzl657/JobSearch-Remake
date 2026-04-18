package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    public List<User> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM users",
                new BeanPropertyRowMapper<>(User.class)
        );
    }

    public Optional<User> findById(Integer id) {
        try {
            User user = jdbcTemplate.queryForObject(
                    "SELECT * FROM users WHERE id = ?",
                    new BeanPropertyRowMapper<>(User.class), id
            );
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM users WHERE email = ?",
                new BeanPropertyRowMapper<>(User.class), email
        );
        return users.stream().findFirst();
    }

    public List<User> findByAccountType(String accountType) {
        return jdbcTemplate.query(
                "SELECT * FROM users WHERE account_type = ?",
                new BeanPropertyRowMapper<>(User.class), accountType
        );
    }

    public void create(User u) {
        String sql = """
                INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type, enabled, role_id)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, TRUE,
                    (SELECT id FROM roles WHERE role = ?))
                """;
        jdbcTemplate.update(sql,
                u.getName(),
                u.getSurname(),
                u.getAge(),
                u.getEmail(),
                u.getPassword(),
                u.getPhoneNumber(),
                u.getAvatar(),
                u.getAccountType(),
                u.getAccountType()
        );
    }

    public void update(User u) {
        String sql = """
                UPDATE users
                SET name=?, surname=?, age=?, phone_number=?, avatar=?
                WHERE id=?
                """;
        jdbcTemplate.update(sql,
                u.getName(),
                u.getSurname(),
                u.getAge(),
                u.getPhoneNumber(),
                u.getAvatar(),
                u.getId()
        );
    }

    public void delete(Integer id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}