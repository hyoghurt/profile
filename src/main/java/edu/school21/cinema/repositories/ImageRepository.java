package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;

import java.util.List;
import java.util.UUID;

public interface ImageRepository {
    boolean existByIdAndUserId(UUID id, Long userId);
    int save(Image image);
    List<Image> findAllByUserId(Long userId);
    String findMimeById(UUID id);
}
