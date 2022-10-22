package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;


@Component
public class UserRepositoryImplJdbcTemplate implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepositoryImplJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(User user) {
        String INSERT = "INSERT INTO users(first_name, last_name, phone, email, password) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(INSERT, user.getFirstName(), user.getLastName(),
                user.getPhone(), user.getEmail(), user.getPassword());
    }

    @Override
    public User findByEmail(String email) {
        try {
            String SELECT_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
            return jdbcTemplate.queryForObject(SELECT_BY_EMAIL,
                    (rs, rowNum) -> new User(
                            rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("phone"),
                            rs.getString("password"),
                            rs.getString("email")),
                    email);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
