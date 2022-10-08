package edu.school21.cinema.services;

import edu.school21.cinema.models.ImageDTO;

import javax.servlet.http.Part;
import java.util.List;
import java.util.UUID;

public interface ImageService {
    byte[] getImageById(String id);
    void saveFile(Part part, Long userId);
    boolean authImages(Long userId, UUID uuid);
    List<ImageDTO> getListImages(Long userId);
    String getMimeById(UUID id);
}
