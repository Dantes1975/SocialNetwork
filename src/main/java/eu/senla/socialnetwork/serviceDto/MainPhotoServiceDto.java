package eu.senla.socialnetwork.serviceDto;

import eu.senla.socialnetwork.dto.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

public interface MainPhotoServiceDto {
    PhotoDto addPhoto(MultipartFile file, Long id) throws FileNotFoundException;

    PhotoDto getPhoto(Long id);

    void delete(Long id);
}
