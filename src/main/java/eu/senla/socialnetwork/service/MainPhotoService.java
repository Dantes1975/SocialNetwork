package eu.senla.socialnetwork.service;

import eu.senla.socialnetwork.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface MainPhotoService {
    Photo addPhoto(MultipartFile file, Long id) throws FileNotFoundException;

    Photo getPhoto(Long id);

    void delete(Long id);
}
