package kg.attractor.jobsearch_remake.dao;

import kg.attractor.jobsearch_remake.model.User;
import lombok.RequiredArgsConstructor;
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

    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        List<User> users = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(User.class), email);

        return users.stream().findFirst();
    }

    public void create(User u) {
        String sql = """
                INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                u.getName(),
                u.getSurname(),
                u.getAge(),
                u.getEmail(),
                u.getPassword(),
                u.getPhoneNumber(),
                u.getAvatar(),
                u.getAccountType());
    }
}