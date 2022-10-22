package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;

import java.util.List;

public interface ImageRepository {
    boolean existByIdAndUserId(String fileId, Long userId);
    int save(Image image);
    List<Image> findAllByUserId(Long userId);
    String findMimeById(String id);
}
