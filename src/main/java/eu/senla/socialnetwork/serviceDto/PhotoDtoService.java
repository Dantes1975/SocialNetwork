package eu.senla.socialnetwork.serviceDto;

import eu.senla.socialnetwork.dto.PhotoDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoDtoService {
    void addPhoto(MultipartFile file, Long id);

    List<PhotoDto> getPhotos(Long id);

    void delete(Long id);
}
