package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ImageRepositoryImplJdbcTemplate implements ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ImageRepositoryImplJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public boolean existByIdAndUserId(UUID id, Long userId) {
        String QUERY = "SELECT COUNT(*) FROM images WHERE id = ? AND user_id = ?";
        return jdbcTemplate.queryForObject(QUERY, Integer.class, id, userId) == 1;
    }

    @Override
    public List<Image> findAllByUserId(Long userId) {
        try {
            String SELECT_IMAGES_BY_PHONE = "SELECT * FROM images WHERE user_id = ?";
            return jdbcTemplate.query(SELECT_IMAGES_BY_PHONE,
                    (rs, rowNum) -> new Image(
                            rs.getObject("id", java.util.UUID.class),
                            rs.getLong("user_id"),
                            rs.getString("name"),
                            rs.getLong("size"),
                            rs.getString("mime")),
                    userId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public String findMimeById(UUID id) {
        try {
            String QUERY = "SELECT mime FROM images WHERE id = ?";
            return jdbcTemplate.queryForObject(QUERY, String.class, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int save(Image image) {
        String INSERT_IMAGE = "INSERT INTO images VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(INSERT_IMAGE, image.getId(), image.getUserId(), image.getName(),
                image.getSize(), image.getMime());
    }
}
