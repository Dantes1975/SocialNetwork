package eu.senla.socialnetwork.serviceDto.impl;

import eu.senla.socialnetwork.dto.PhotoDto;
import eu.senla.socialnetwork.model.Photo;
import eu.senla.socialnetwork.service.PhotoService;
import eu.senla.socialnetwork.serviceDto.PhotoDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserPhotoDtoService implements PhotoDtoService {

    private final PhotoService photoService;

    @Autowired
    public UserPhotoDtoService(PhotoService photoService) {
        this.photoService = photoService;
    }

    @Override
    public void addPhoto(MultipartFile file, Long id) {
        photoService.addPhoto(file, id);
    }

    @Override
    public List<PhotoDto> getPhotos(Long id) {
        List<Photo> photos = photoService.getPhotos(id);
        List<PhotoDto> result = new ArrayList<>();
        for (Photo photo : photos) {
            result.add(PhotoDto.fromPhoto(photo));
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        photoService.delete(id);
    }
}
