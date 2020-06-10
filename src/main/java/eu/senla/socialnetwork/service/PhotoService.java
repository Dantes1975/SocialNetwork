package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    void addPhoto(MultipartFile file, Long id);

    List<Photo> getPhotos(Long id);

    void delete(Long id);
}
