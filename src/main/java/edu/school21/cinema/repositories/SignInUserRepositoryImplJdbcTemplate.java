package edu.school21.cinema.repositories;

import edu.school21.cinema.models.SignIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class SignInUserRepositoryImplJdbcTemplate implements SignInUserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SignInUserRepositoryImplJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int save(SignIn signIn) {
        String QUERY = "INSERT INTO signin_users (user_id, date, ip) VALUES (?, ?, ?)";
        return jdbcTemplate.update(QUERY, signIn.getUserId(), signIn.getDate(), signIn.getIp());
    }

    @Override
    public List<SignIn> findAllByUserId(Long userId) {
        try {
            String QUERY = "SELECT * FROM signin_users WHERE user_id = ?";
            return jdbcTemplate.query(QUERY,
                    (rs, rowNum) -> new SignIn(
                            rs.getLong("id"),
                            rs.getLong("user_id"),
                            rs.getTimestamp("date"),
                            rs.getString("ip")),
                    userId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
