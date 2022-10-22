package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.models.ImageDTO;
import edu.school21.cinema.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource("file:${webapp.root}/WEB-INF/application.properties")
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    @Value("${path.file.upload}")
    private String pathFileUpload;

    @Override
    public boolean authImages(Long userId, String fileId) {
        return imageRepository.existByIdAndUserId(fileId, userId);
    }

    @Override
    public List<ImageDTO> getListImages(Long userId) {
        List<Image> imageList = imageRepository.findAllByUserId(userId);

        return imageList.stream().map(e -> new ImageDTO(e.getId(), e.getName(),
                        FileUtils.byteCountToDisplaySize(e.getSize()), e.getMime()))
                .collect(Collectors.toList());
    }

    @Override
    public String getMimeById(String id) {
        return imageRepository.findMimeById(id);
    }

    @Override
    public byte[] getImageById(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pathFileUpload);
        if (!pathFileUpload.endsWith("/"))
            stringBuilder.append("/");
        stringBuilder.append(id);

        try {
            return Files.readAllBytes(Paths.get(stringBuilder.toString()));
        } catch (IOException e) {
            log.error("not found file: " + stringBuilder);
            return null;
        }
    }

    @Override
    public void saveFile(Part part, Long userId) {
        try {
            Image image = new Image();

            image.setId(UUID.randomUUID() + "." + part.getContentType().split("/")[1]);
            image.setUserId(userId);
            image.setName(part.getSubmittedFileName());
            image.setMime(part.getContentType());
            image.setSize(part.getSize());

            saveFileLocal(part, image.getId());
            saveImageRepo(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveFileLocal(Part part, String id) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pathFileUpload);
        if (!pathFileUpload.endsWith("/"))
            stringBuilder.append("/");
        stringBuilder.append(id);

        Path path = Paths.get(stringBuilder.toString());
        if (!path.getParent().toFile().exists())
            Files.createDirectories(path.getParent());
        part.write(stringBuilder.toString());
        log.info("create file local - success: {}", stringBuilder);
    }

    private void saveImageRepo(Image image) {
        if (imageRepository.save(image) == 1) {
            log.info("save image db - success: {}", image.getId());
        } else {
            log.error("save image db - fail: {}", image.getId());
        }
    }
}
