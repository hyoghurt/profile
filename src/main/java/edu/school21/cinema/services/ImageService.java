package edu.school21.cinema.services;

import edu.school21.cinema.models.ImageDTO;

import javax.servlet.http.Part;
import java.util.List;

public interface ImageService {
    byte[] getImageById(String id);
    void saveFile(Part part, Long userId);
    boolean authImages(Long userId, String fileId);
    List<ImageDTO> getListImages(Long userId);
    String getMimeById(String id);
}
